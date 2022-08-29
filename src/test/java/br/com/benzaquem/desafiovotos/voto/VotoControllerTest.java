package br.com.benzaquem.desafiovotos.voto;


import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Sql({"/data/voto/import_data_for_voto_controller_test.sql"})
class VotoControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup(){
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

        var responseContent = "{\"mensagem\":\"Seu voto foi computado com sucesso.\"}";

        mockMvcVotosTest(requestContent, responseContent, MockMvcResultMatchers.status().isOk());
    }

    @SneakyThrows
    @Test
    void votarSegundaVezPautaSessaoAbertaClienteValidoComErroRetorna400() {
        var requestContent = "{" +
                "\"id_pauta\":1," +
                "\"id_associado\":1," +
                "\"opcao\":\"SIM\"" +
                "}";

        var responseContent = "{" +
                "\"mensagem\":\"Não é possível votar mais de uma vez na mesma pauta.\"" +
                "}";

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

        var responseContent = "{" +
                "\"mensagem\":\"Não foi possível processar o seu voto, por não existir sessão aberta para pauta id= 2.\"" +
                "}";

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

    private void mockMvcVotosTest(String requestContent, String responseContent, ResultMatcher resultMatchers) throws Exception {
        var uri = URI.create("/votos");

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(resultMatchers)
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(responseContent));
    }

}
