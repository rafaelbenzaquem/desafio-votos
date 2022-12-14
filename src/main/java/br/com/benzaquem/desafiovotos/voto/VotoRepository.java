package br.com.benzaquem.desafiovotos.voto;

import br.com.benzaquem.desafiovotos.voto.model.Voto;
import br.com.benzaquem.desafiovotos.voto.model.VotoPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, VotoPk> {

    @Query(nativeQuery = true, value = "SELECT *  FROM voto v WHERE EXISTS (SELECT pauta_id FROM sessao s WHERE v.sessao_id = s.id and s.pauta_id=:idPauta and v.associado_id=:idAssociado)")
    Optional<Voto> findVotoByAssociadoAndPauta(@Param("idAssociado") Long idAssociado, @Param("idPauta") Long idPauta);

    @Query(value = "select case when (count(v.opcao)>0) then true else false end from Voto v where exists (" +
            "select s.pauta.id from Sessao s where v.id.sessao.id = s.id and s.pauta.id=:idPauta and v.id.associado.id=:idAssociado" +
            ")")
    boolean existsVotoByAssociadoAndPauta(@Param("idAssociado") Long idAssociado, @Param("idPauta") Long idPauta);

    @Query(nativeQuery = true, value = "SELECT count(opcao)  FROM voto v WHERE v.opcao=:opcao and EXISTS (SELECT pauta_id FROM sessao s WHERE v.sessao_id = s.id and s.pauta_id=:idPauta)")
    int contarVotosPorPautaEOpcao(@Param("idPauta") Long idPauta, @Param("opcao") String opcao);

    @Query(nativeQuery = true, value = "SELECT opcao  FROM voto v WHERE EXISTS (SELECT pauta_id FROM sessao s WHERE v.sessao_id = s.id and s.pauta_id=:idPauta)")
    List<String> findVotosPorPauta(@Param("idPauta") Long idPauta);

}
