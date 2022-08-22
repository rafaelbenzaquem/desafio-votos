package br.com.benzaquem.desafiovotos.sessao;

import br.com.benzaquem.desafiovotos.pauta.PautaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class SessaoVotacaoRepositoryTest {


    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;



    @Test
    void buscarUltimaSessaoVotacaoAbertaPorIdPauta() {
        Optional<Sessao> optSessao = sessaoVotacaoRepository.buscarUltimaSessaoAbertaPorPauta(1L, LocalDateTime.now());

        assertTrue(optSessao.isPresent());

        assertEquals(2L, optSessao.get().getId());
    }

}