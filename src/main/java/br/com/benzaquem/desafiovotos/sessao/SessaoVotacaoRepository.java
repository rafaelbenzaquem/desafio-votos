package br.com.benzaquem.desafiovotos.sessao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoVotacaoRepository extends JpaRepository<Sessao,Long> {
}
