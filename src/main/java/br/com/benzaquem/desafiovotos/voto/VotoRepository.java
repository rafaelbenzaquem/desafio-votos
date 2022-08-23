package br.com.benzaquem.desafiovotos.voto;

import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, VotoPk> {

    @Query(nativeQuery = true, value = "SELECT *  FROM voto v WHERE EXISTS (SELECT pauta_id FROM sessao s WHERE v.sessao_id = s.id and s.pauta_id=:idPauta and v.associado_id=:idAssociado)")
    Optional<Value> findVotoByAssociadoAndPauta(@Param("idAssociado") Long idAssociado, @Param("idPauta") Long idPauta);

    @Query(nativeQuery = true, value = "SELECT count(opcao)>0  FROM voto v WHERE EXISTS (SELECT pauta_id FROM sessao s WHERE v.sessao_id = s.id and s.pauta_id=:idPauta and v.associado_id=:idAssociado)")
    boolean existsVotoByAssociadoAndPauta(@Param("idAssociado") Long idAssociado, @Param("idPauta") Long idPauta);

}
