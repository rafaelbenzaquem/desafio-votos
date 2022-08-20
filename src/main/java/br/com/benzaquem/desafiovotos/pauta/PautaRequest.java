package br.com.benzaquem.desafiovotos.pauta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PautaRequest {

    private final String nome;

    public PautaRequest(@JsonProperty(value = "nome") String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Pauta toModel() {
        return new Pauta(null, nome);
    }

}
