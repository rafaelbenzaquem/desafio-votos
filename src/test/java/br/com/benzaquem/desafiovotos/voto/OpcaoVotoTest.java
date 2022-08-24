package br.com.benzaquem.desafiovotos.voto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpcaoVotoTest {


    @Test
    void testEnumOpcaoVotoOf() {

        assertAll("Criacao da Enum 'OpcaoVoto'",
                () -> assertEquals(OpcaoVoto.SIM, OpcaoVoto.of("sim")),
                () -> assertEquals(OpcaoVoto.SIM, OpcaoVoto.of("sIM")),
                () -> assertEquals(OpcaoVoto.NAO, OpcaoVoto.of("nÃO")),
                () -> assertEquals(OpcaoVoto.NAO, OpcaoVoto.of("nao"))
        );

    }

}