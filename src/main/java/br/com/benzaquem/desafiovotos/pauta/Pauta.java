package br.com.benzaquem.desafiovotos.pauta;

import br.com.benzaquem.desafiovotos.sessao.Sessao;

import javax.persistence.*;
import java.util.List;

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
}
