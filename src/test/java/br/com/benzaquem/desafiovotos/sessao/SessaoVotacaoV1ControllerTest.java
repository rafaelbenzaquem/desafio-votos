package br.com.benzaquem.desafiovotos.sessao;

import br.com.benzaquem.desafiovotos.util.KafkaConsumerTestUtil;
import br.com.benzaquem.desafiovotos.resultado.ResultadoVotacaoPublisher;
import lombok.SneakyThrows;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class SessaoVotacaoV1ControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private KafkaConsumerTestUtil consumer;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private  ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private ResultadoVotacaoPublisher resultadoVotacaoPublisher;

//    @MockBean
//    private KafkaTemplate<String, String> kafkaTemplate;


//    @Value("${topico.nome}")
//    private String topico;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    private ScheduledFuture createComand(Runnable command) {
        return new ScheduledThreadPoolExecutor(1).schedule(command, 1L, TimeUnit.MILLISECONDS);
    }

    @SneakyThrows
    @Test
    @Sql("/data/sessao/import_data_for_sessao_votacao_controller_test.sql")
    void abrirSessaoVotacaoTempoDefaultComSucessoRetorna201() {

        var requestContent = "{" +
                "\"nome\":\"Sessao de votacao extraordinária para Investimento\"," +
                "\"id_pauta\":1\n" +
                "}";

        var payload = """
                {
                    "id_pauta":1,
                    "status":"resultado parcial",
                    "SIM":0,
                    "NAO":0
                }
                """;

        var task = new SessaoEncerradaRunnableTask(1L,resultadoVotacaoPublisher);
        Mockito.when(threadPoolTaskScheduler.schedule(Mockito.eq(task),Mockito.any(Date.class))).thenReturn(createComand(task));

        var uri = new URI("/v1/sessoes");

        mockMvc.perform(
                        MockMvcRequestBuilders.post(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestContent))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(MockMvcResultMatchers.header().string("Location", MatchesPattern.matchesPattern("http://localhost/v1/sessoes/(\\d+)")));

        boolean messageConsumed = consumer.getLatch().await(2, TimeUnit.SECONDS);
        assertTrue(messageConsumed);
        assertEquals(consumer.getPayload(), payload);

    }

    @SneakyThrows
    @Test
    void abrirSessaoVotacaoComPautaInexistente404() {

        var requestContent = "{" +
                "\"nome\":\"Sessao de votacao extraordinária para Investimento\"," +
                "\"id_pauta\":99\n" +
                "}";

        var responseContent = "{\"status\":404,\"mensagem\":\"Recurso não encontrado!\",\"campos\":[{\"campo\":\"id_pauta\",\"mensagem\":\"Pauta id = 99 não existe.\"}]}";

//        var task = new SessaoEncerradaRunnableTask(1L,resultadoVotacaoPublisher);
//        Mockito.when(threadPoolTaskScheduler.schedule(Mockito.eq(task),Mockito.any(Date.class))).thenReturn(createComand(task));

        var uri = new URI("/v1/sessoes");

        mockMvc.perform(
                        MockMvcRequestBuilders.post(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestContent))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(responseContent));

    }

}
