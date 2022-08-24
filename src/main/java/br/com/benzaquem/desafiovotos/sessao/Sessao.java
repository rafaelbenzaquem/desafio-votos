package br.com.benzaquem.desafiovotos.sessao;

import br.com.benzaquem.desafiovotos.pauta.Pauta;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Sessao implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDateTime inicio;

    private LocalDateTime fim;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    public Sessao() {
    }

    public Sessao(Long id, String nome, LocalDateTime inicio, LocalDateTime fim, Pauta pauta) {
        this.id = id;
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
        this.pauta = pauta;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public Pauta getPauta() {
        return pauta;
    }
}
