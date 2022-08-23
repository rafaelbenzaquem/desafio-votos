package br.com.benzaquem.desafiovotos.voto;


import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class VotoControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @SneakyThrows
    @Test
    void votarPautaSessaoAbertaClienteValidoComSucessoRetorna200() {
        var requestContent = "{" +
                "\"id_pauta\":2," +
                "\"id_associado\":1," +
                "\"opcao\":\"SIM\"" +
                "}";

        var responseContent = "{" +
                "\"mensagem\":\"Seu voto foi computado com sucesso.\"" +
                "}";

        var uri = URI.create("/votos");

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(responseContent));


    }

    @SneakyThrows
    @Test
    void votarSegundaVezPautaSessaoAbertaClienteValidoComErroRetorna422(){
        var requestContent = "{" +
                "\"id_pauta\":1," +
                "\"id_associado\":1," +
                "\"opcao\":\"SIM\"" +
                "}";
        System.out.println(requestContent);
        var responseContent = "{" +
                "\"mensagem\":\"Não é possível votar mais de uma vez na mesma pauta.\"" +
                "}";

        var uri = URI.create("/votos");

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(responseContent));
    }



}
