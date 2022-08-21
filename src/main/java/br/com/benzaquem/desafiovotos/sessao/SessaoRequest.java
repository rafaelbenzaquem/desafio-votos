package br.com.benzaquem.desafiovotos.sessao;

import br.com.benzaquem.desafiovotos.pauta.Pauta;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class SessaoRequest {

    private String nome;

    @JsonProperty(value = "duracao")
    private Long duracaoEmMinutos;

    @JsonProperty(value = "id_pauta")
    private Long idPauta;

    public SessaoRequest() {
    }

    public SessaoRequest(String nome, Long duracaoEmMinutos, Long idPauta) {
        this.nome = nome;
        this.duracaoEmMinutos = duracaoEmMinutos;
        this.idPauta = idPauta;
    }

    public String getNome() {
        return nome;
    }


    public Long getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public Sessao toModel(Pauta pauta) {
        duracaoEmMinutos = duracaoEmMinutos == null ? 1 : duracaoEmMinutos;
        var dataInicial = LocalDateTime.now();
        var dataFinal = dataInicial.plusMinutes(duracaoEmMinutos);
        return new Sessao(null, nome, dataInicial, dataFinal, pauta);
    }
}
