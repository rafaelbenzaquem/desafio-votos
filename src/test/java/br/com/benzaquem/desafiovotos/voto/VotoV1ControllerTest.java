package br.com.benzaquem.desafiovotos.voto;


import br.com.benzaquem.desafiovotos.analise.AnaliseCpfExternalService;
import br.com.benzaquem.desafiovotos.analise.AnaliseCpfResponse;
import br.com.benzaquem.desafiovotos.util.FeignExceptionForTests;
import feign.FeignException;
import feign.Request;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Sql({"/data/voto/import_data_for_voto_controller_test.sql"})
class VotoV1ControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private AnaliseCpfExternalService analiseCpfExternalService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @SneakyThrows
    @Test
    void votarPautaSessaoAbertaClienteValidoComSucessoRetorna200() {

        var requestContent = "{" +
                "\"id_pauta\":1," +
                "\"id_associado\":2," +
                "\"opcao\":\"SIM\"" +
                "}";

        Mockito.when(analiseCpfExternalService.solicitarAnaliseVotoPorCpf(Mockito.anyString())).thenReturn(new AnaliseCpfResponse("ABLE_TO_VOTE"));

        var responseContent = "{\"status\":200,\"mensagem\":\"Seu voto foi computado com sucesso.\"}";

        mockMvcVotosTest(requestContent, responseContent, MockMvcResultMatchers.status().isOk());
    }

    @SneakyThrows
    @Test
    void votarPautaSessaoAbertaClienteValidoNaoHabilitadoRetorna422() {

        var requestContent = "{" +
                "\"id_pauta\":1," +
                "\"id_associado\":2," +
                "\"opcao\":\"SIM\"" +
                "}";

        Mockito.when(analiseCpfExternalService.solicitarAnaliseVotoPorCpf(Mockito.anyString())).thenReturn(new AnaliseCpfResponse("UNABLE_TO_VOTE"));

        var responseContent = "{\"status\":422,\"mensagem\":\"O associado id = 2 não está habilitado para votar na pauta id = 1.\"}";

        mockMvcVotosTest(requestContent, responseContent, MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @SneakyThrows
    @Test
    void votarSegundaVezPautaSessaoAbertaClienteValidoComErroRetorna400() {
        var requestContent = "{" +
                "\"id_pauta\":1," +
                "\"id_associado\":1," +
                "\"opcao\":\"SIM\"" +
                "}";


        Mockito.when(analiseCpfExternalService.solicitarAnaliseVotoPorCpf(Mockito.anyString())).thenReturn(new AnaliseCpfResponse("ABLE_TO_VOTE"));


        var responseContent = "{\"status\":400,\"mensagem\":\"Não é possível votar mais de uma vez na mesma pauta.\"}";

        var uri = URI.create("/votos");

        mockMvcVotosTest(requestContent, responseContent, MockMvcResultMatchers.status().isBadRequest());
    }


    @SneakyThrows
    @Test
    void votarPautaSessaoFechadaClienteValidoComErroRetorna400() {
        var requestContent = "{" +
                "\"id_pauta\":2," +
                "\"id_associado\":1," +
                "\"opcao\":\"SIM\"" +
                "}";


        Mockito.when(analiseCpfExternalService.solicitarAnaliseVotoPorCpf(Mockito.anyString())).thenReturn(new AnaliseCpfResponse("ABLE_TO_VOTE"));


        var responseContent = "{\"status\":400,\"mensagem\":\"Não foi possível processar o seu voto, por não existir sessão aberta para pauta id= 2.\"}";

        mockMvcVotosTest(requestContent, responseContent, MockMvcResultMatchers.status().isBadRequest());
    }

    @SneakyThrows
    @Test
    void votarPautaErroNoServicoExterno() {
        var requestContent = "{" +
                "\"id_pauta\":2," +
                "\"id_associado\":1," +
                "\"opcao\":\"SIM\"" +
                "}";


        Mockito.when(analiseCpfExternalService.solicitarAnaliseVotoPorCpf(Mockito.anyString())).thenThrow(new FeignExceptionForTests(400,"teste"));


        var responseContent = "{\"status\":400,\"erro\":\"Erro de serviço externo!\",\"mensagem\":\"teste\"}";

        mockMvcVotosTest(requestContent, responseContent, MockMvcResultMatchers.status().isBadRequest());
    }

    @SneakyThrows
    @Test
    void votarPautaInexistenteClienteValidoComErroRetorna400() {
        var requestContent = "{" +
                "\"id_pauta\":1," +
                "\"id_associado\":99," +
                "\"opcao\":\"SIM\"" +
                "}";

        var responseContent = "{\"status\":400,\"mensagem\":\"Erro de validação!\",\"campos\":[{\"campo\":\"id_associado\",\"mensagem\":\"Não existe um valor cadastrado na base de dados.\"}]}";

        mockMvcVotosTest(requestContent, responseContent, MockMvcResultMatchers.status().isBadRequest());
    }

    @SneakyThrows
    @Test
    void votarPautaClienteInexistenteComErroRetorna400() {
        var requestContent = "{" +
                "\"id_pauta\":99," +
                "\"id_associado\":1," +
                "\"opcao\":\"SIM\"" +
                "}";
        System.out.println(requestContent);
        var responseContent = "{\"status\":400,\"mensagem\":\"Erro de validação!\",\"campos\":[{\"campo\":\"id_pauta\",\"mensagem\":\"Não existe um valor cadastrado na base de dados.\"}]}";

        mockMvcVotosTest(requestContent, responseContent, MockMvcResultMatchers.status().isBadRequest());
    }


    @SneakyThrows
    @Test
    void votarEmPautaValidaComClienteValidoOpcaoInvalidaRetorna400() {
        var requestContent = "{" +
                "\"id_pauta\":1," +
                "\"id_associado\":1," +
                "\"opcao\":\"talvez\"" +
                "}";
        System.out.println(requestContent);
        var responseContent = "{\"status\":400,\"mensagem\":\"Erro de validação!\",\"campos\":[{\"campo\":\"opcao\",\"mensagem\":\"Opção escolhida é inválida, escolha['sim','não']\"}]}";

        mockMvcVotosTest(requestContent, responseContent, MockMvcResultMatchers.status().isBadRequest());
    }

    private void mockMvcVotosTest(String requestContent, String responseContent, ResultMatcher resultMatchers) throws Exception {
        var uri = URI.create("/v1/votos");

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(resultMatchers)
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(responseContent));
    }

}
