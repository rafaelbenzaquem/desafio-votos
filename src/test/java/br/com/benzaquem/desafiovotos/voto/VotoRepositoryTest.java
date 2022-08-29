package br.com.benzaquem.desafiovotos.voto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class VotoRepositoryTest {

    @Autowired
    private VotoRepository votoRepository;


    @Test
    @Sql({"/data/voto/import_data_for_voto_repository_test.sql"})
    void buscaVotoDeAssociadoEmUmaPautaTest() {
        var opt = votoRepository.findVotoByAssociadoAndPauta(2L, 1L);
        assertTrue(opt.isPresent());
    }

    @Test
    @Sql({"/data/voto/import_data_for_voto_repository_test.sql"})
    void verificaSeExisteVotoDeAssociadoEmUmaPautaTest() {
        var exist = votoRepository.existsVotoByAssociadoAndPauta(2L, 1L);
        assertTrue(exist);
    }

    @Test
    @Sql({"/data/voto/import_data_for_voto_repository_test.sql"})
    void contarVotosPorPautaEOpcaoTest() {
        var qtSIM = votoRepository.contarVotosPorPautaEOpcao(1L, OpcaoVoto.SIM.name());
        var qtNAO = votoRepository.contarVotosPorPautaEOpcao(3L, OpcaoVoto.NAO.name());
        assertEquals(1, qtSIM);
        assertEquals(3, qtNAO);
    }

    @Test
    @Sql({"/data/voto/import_data_for_voto_repository_test.sql"})
    void findVotosPorPautaTest(){
        var list = votoRepository.findVotosPorPauta(3L);

       var qtSIM = list.stream().filter(s -> s.equalsIgnoreCase("sim")).count();
       var qtNAO = list.stream().filter(s -> s.equalsIgnoreCase("nao")).count();

        assertEquals(0, qtSIM);
        assertEquals(3, qtNAO);

        assertEquals(3,list.size());
    }

}