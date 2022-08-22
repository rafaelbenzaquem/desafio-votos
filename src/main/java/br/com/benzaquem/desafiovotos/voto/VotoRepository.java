package br.com.benzaquem.desafiovotos.voto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, VotoPk> {
}
