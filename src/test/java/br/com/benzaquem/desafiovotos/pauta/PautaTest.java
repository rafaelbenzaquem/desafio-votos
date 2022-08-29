package br.com.benzaquem.desafiovotos.pauta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PautaTest {

    @Test
    void getNome() {
    }

    @Test
    void testEquals() {
        Pauta pautaA = new Pauta(1L, "testeA");
        Pauta pautaB = new Pauta(1L, "testeB");
        Pauta pautaC = new Pauta(2L, "testeA");
        Pauta pautaD = null;

        assertEquals(pautaA, pautaA);
        assertFalse(pautaA.equals(pautaD));
        assertEquals(pautaA, pautaB);
        assertEquals(pautaA.getNome(), pautaC.getNome());
        assertNotEquals(pautaA, pautaC);
    }

    @Test
    void testHashCode() {
        Pauta pautaA = new Pauta(1L, "testeA");
        Pauta pautaB = new Pauta(1L, "testeB");
        Pauta pautaC = new Pauta(2L, "testeA");


        assertEquals(pautaA.hashCode(), pautaA.hashCode());
        assertEquals(pautaA.hashCode(), pautaB.hashCode());
        assertNotEquals(pautaA.hashCode(), pautaC.hashCode());
    }
}