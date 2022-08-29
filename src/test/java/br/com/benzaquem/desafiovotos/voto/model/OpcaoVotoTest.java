package br.com.benzaquem.desafiovotos.voto.model;

import br.com.benzaquem.desafiovotos.voto.exceptions.OpcaoVotoInvalidoException;
import br.com.benzaquem.desafiovotos.voto.model.OpcaoVoto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpcaoVotoTest {

    @Test
    void enumOpcaoVotoValida() {
        assertAll("Criacao da Enum 'OpcaoVoto'",
                () -> assertEquals(OpcaoVoto.SIM, OpcaoVoto.of("sim")),
                () -> assertEquals(OpcaoVoto.SIM, OpcaoVoto.of("sIM")),
                () -> assertEquals(OpcaoVoto.NAO, OpcaoVoto.of("nÃO")),
                () -> assertEquals(OpcaoVoto.NAO, OpcaoVoto.of("nao"))
        );
    }

    @Test
    void enumOpcaoVotoInvalida() {

        assertThrows(OpcaoVotoInvalidoException.class, () -> OpcaoVoto.of("talvez"), "Opção de voto inválida!");

    }

}