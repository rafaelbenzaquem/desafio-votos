package br.com.benzaquem.desafiovotos.associado;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssociadoTest {


    @Test
    void testEquals() {
        var associadoA = Associado.
                builder()
                .id(1L)
                .nome("Rafael")
                .build();

        Object associadoB = Associado.
                builder()
                .id(1L)
                .nome("Daniel")
                .build();

        var associadoC = Associado.
                builder()
                .id(2L)
                .nome("Daniel")
                .build();
        assertEquals(associadoA, associadoA);
        assertNotEquals(associadoA, null);
        assertFalse(associadoA.equals(""));
        assertEquals(associadoA, associadoB);
        assertNotEquals(associadoA, associadoC);

    }

    @Test
    void testHashCode() {
        var associadoA = Associado.
                builder()
                .id(1L)
                .nome("Rafael")
                .build();

        Object associadoB = Associado.
                builder()
                .id(1L)
                .nome("Daniel")
                .build();

        var associadoC = Associado.
                builder()
                .id(2L)
                .nome("Daniel")
                .build();
        assertEquals(associadoA.hashCode(), associadoB.hashCode());
        assertNotEquals(associadoA.hashCode(), associadoC.hashCode());
    }

}