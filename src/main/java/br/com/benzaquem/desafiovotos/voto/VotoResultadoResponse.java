package br.com.benzaquem.desafiovotos.voto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VotoResultadoResponse {

    private String status;
    @JsonProperty("SIM")
    private Long quantidadeSim;
    @JsonProperty("NAO")
    private Long quantidadeNao;

    public VotoResultadoResponse() {
    }

    public String getStatus() {
        return status;
    }

    public Long getQuantidadeSim() {
        return quantidadeSim;
    }

    public Long getQuantidadeNao() {
        return quantidadeNao;
    }

    public VotoResultadoResponse(String status, Long quantidadeSim, Long quantidadeNao) {
        this.status = status;
        this.quantidadeSim = quantidadeSim;
        this.quantidadeNao = quantidadeNao;
    }

    public static VotoResultadoResponse of(String status, long quantidadeSim, long quantidadeNao) {
        return new VotoResultadoResponse(status,quantidadeSim,quantidadeNao);
    }
}
