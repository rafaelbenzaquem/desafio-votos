package br.com.benzaquem.desafiovotos.pauta;

import br.com.benzaquem.desafiovotos.sessao.Sessao;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "pauta")
    private List<Sessao> sessoes;

    public Pauta() {
    }

    public Pauta(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pauta pauta = (Pauta) o;
        return Objects.equals(id, pauta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return """
                {
                    "id" : "%d",
                    "nome" : "%s"
                }
                """.formatted(id, nome);
    }
}
