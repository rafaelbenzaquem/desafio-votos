package br.com.benzaquem.desafiovotos.associado;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;


@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class AssociadoRepositoryTest {


    @Autowired
    AssociadoRepository associadoRepository;


    @Test
    @Sql({"/data/associado/import_data_for_associado_repository_test.sql"})
    void existeAssociadoByCpfTest() {
        boolean exist = associadoRepository.existsAssociadoByCpf("75124377062");
        Assertions.assertTrue(exist);
    }

}