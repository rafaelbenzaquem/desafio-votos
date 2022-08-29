package br.com.benzaquem.desafiovotos.sessao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class SessaoVotacaoRepositoryTest {


    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;


    @Test
    @Sql("/data/sessao/import_data_for_sessao_votacao_controller_test.sql")
    void buscarUltimaSessaoVotacaoAbertaPorIdPauta() {
        Optional<Sessao> optSessao = sessaoVotacaoRepository.buscarUltimaSessaoAbertaPorPauta(2L, LocalDateTime.now());

        assertTrue(optSessao.isPresent());

        assertEquals(4L, optSessao.get().getId());
    }

}