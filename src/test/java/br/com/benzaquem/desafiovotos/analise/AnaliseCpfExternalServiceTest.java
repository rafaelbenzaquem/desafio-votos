package br.com.benzaquem.desafiovotos.analise;

import br.com.benzaquem.desafiovotos.analise.AnaliseCpfExternalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AnaliseCpfExternalServiceTest {

    @Autowired
    private AnaliseCpfExternalService analiseCpfExternalService;


    @Test
    void analisarVotoValidoServicoExterno() {

        var analiseVotoResponse = analiseCpfExternalService.solicitarAnaliseVotoPorCpf("77643070253");

        assertNotNull(analiseVotoResponse);

        assertNotNull(analiseVotoResponse.status());

    }

}