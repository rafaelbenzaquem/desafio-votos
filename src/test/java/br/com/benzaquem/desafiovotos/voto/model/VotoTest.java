package br.com.benzaquem.desafiovotos.voto.model;

import br.com.benzaquem.desafiovotos.associado.Associado;
import br.com.benzaquem.desafiovotos.sessao.Sessao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VotoTest {

    @Test
    void testEquals() {
        var votoA = new Voto(
                new VotoPk(
                        new Associado(1L,null,null),
                        new Sessao(1L,null,null,null,null)),
                OpcaoVoto.NAO);
        var votoB = new Voto(
                new VotoPk(
                        new Associado(1L,null,null),
                        new Sessao(1L,null,null,null,null)),
                OpcaoVoto.SIM);
        var votoC = new Voto(
                new VotoPk(
                        new Associado(2L,null,null),
                        new Sessao(1L,null,null,null,null)),
                OpcaoVoto.NAO);

        assertEquals(votoA,votoB);
        assertEquals(votoA,votoA);
        assertNotEquals(votoA,votoC);

    }

    @Test
    void testHashCode() {
        var votoA = new Voto(
                new VotoPk(
                        new Associado(1L,null,null),
                        new Sessao(1L,null,null,null,null)),
                OpcaoVoto.NAO);
        var votoB = new Voto(
                new VotoPk(
                        new Associado(1L,null,null),
                        new Sessao(1L,null,null,null,null)),
                OpcaoVoto.SIM);
        var votoC = new Voto(
                new VotoPk(
                        new Associado(2L,null,null),
                        new Sessao(1L,null,null,null,null)),
                OpcaoVoto.NAO);

        assertEquals(votoA.hashCode(),votoB.hashCode());
        assertNotEquals(votoA.hashCode(),votoC.hashCode());
    }
}