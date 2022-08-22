package br.com.benzaquem.desafiovotos.voto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VotoResponse {

    private String mensagem;

    public VotoResponse(@JsonProperty("mensagem") String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
