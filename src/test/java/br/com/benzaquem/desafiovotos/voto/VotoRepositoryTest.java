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
    void buscaVotoDeAssociadoEmUmaPautaTest() {
        var opt = votoRepository.findVotoByAssociadoAndPauta(2L, 1L);
        assertTrue(opt.isPresent());
    }

    @Test
    void verificaSeExisteVotoDeAssociadoEmUmaPautaTest() {
        var exist = votoRepository.existsVotoByAssociadoAndPauta(2L, 1L);
        assertTrue(exist);
    }

    @Test
    void contarVotosPorPautaEOpcaoTest() {
        var qtSIM = votoRepository.contarVotosPorPautaEOpcao(1L, OpcaoVoto.SIM.name());
        var qtNAO = votoRepository.contarVotosPorPautaEOpcao(3L, OpcaoVoto.NAO.name());
        assertEquals(1, qtSIM);
        assertEquals(3, qtNAO);
    }

    @Test
    void findVotosPorPautaTest(){
        var list = votoRepository.findVotosPorPauta(3L);

       var qtSIM = list.stream().filter(s -> s.equalsIgnoreCase("sim")).count();
       var qtNAO = list.stream().filter(s -> s.equalsIgnoreCase("nao")).count();

        assertEquals(0, qtSIM);
        assertEquals(3, qtNAO);

        assertEquals(3,list.size());
    }

}