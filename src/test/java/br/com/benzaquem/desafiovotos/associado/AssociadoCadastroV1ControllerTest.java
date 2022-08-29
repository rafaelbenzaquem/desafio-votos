package br.com.benzaquem.desafiovotos.associado;


import lombok.SneakyThrows;
import org.hamcrest.text.MatchesPattern;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AssociadoCadastroV1ControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @SneakyThrows
    @Test
    void cadastrarAssociadoComSucessoRetorna201() {
        var requestContent = "{" +
                "\"nome\":\"rafael\"," +
                "\"cpf\" :\"84115667818\"" +
                "}";

        var uri = new URI("/v1/associados");



        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(requestContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", MatchesPattern.matchesPattern("http://localhost/v1/associados/(\\d+)")));

    }

    @SneakyThrows
    @Test
    @Sql({"/data/associado/import_data_for_associado_cadastro_controle_test.sql"})
    void cadastrarAssociadoComDocumentoDuplicadoRetorna400() {
        var requestContent = "{" +
                "\"nome\":\"rafael\"," +
                "\"cpf\" :\"46960387000\"" +
                "}";

        var responseContent = "{\"campo\":\"cpf\",\"mensagem\":\"Documento já cadastrado, N° = 46960387000\"}";

        var uri = new URI("/v1/associados");

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(requestContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(responseContent));

    }

}
