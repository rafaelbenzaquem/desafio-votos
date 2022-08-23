package br.com.benzaquem.desafiovotos.voto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class VotoRepositoryTest {

    @Autowired
    private VotoRepository votoRepository;


    @Test
    void buscaVotoDeAssociadoEmUmaPauta() {
        var opt = votoRepository.findVotoByAssociadoAndPauta(2L, 1L);
        assertTrue(opt.isPresent());
    }

    @Test
    void verificaSeExisteVotoDeAssociadoEmUmaPauta() {
        var exist = votoRepository.existsVotoByAssociadoAndPauta(2L, 1L);
        assertTrue(exist);
    }

}