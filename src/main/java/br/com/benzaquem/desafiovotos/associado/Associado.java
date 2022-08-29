package br.com.benzaquem.desafiovotos.associado;


import lombok.Builder;
import lombok.Getter;

import static br.com.benzaquem.desafiovotos.commons.util.OfuscaDadosUtil.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Builder
@Entity
public class Associado implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    public Associado() {
    }

    public Associado(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Associado associado = (Associado) o;
        return Objects.equals(id, associado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Associado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + ofuscaCpf(cpf) + '\'' +
                '}';
    }
}
