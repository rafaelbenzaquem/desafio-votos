package br.com.benzaquem.desafiovotos.sessao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SessaoVotacaoRepository extends JpaRepository<Sessao, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM sessao WHERE pauta_id=:idPauta and fim>:agora LIMIT 1;")
    Optional<Sessao> buscarUltimaSessaoAbertaPorPauta(@Param("idPauta") long idPauta,@Param("agora") LocalDateTime agora);

}
