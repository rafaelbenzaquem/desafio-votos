package br.com.benzaquem.desafiovotos.associado;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    boolean existsAssociadoByCpf(String cpf);
}
