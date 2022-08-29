package br.com.benzaquem.desafiovotos.resultado;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultadoVotacaoResponse {

    private String status;
    @JsonProperty("SIM")
    private Long quantidadeSim;
    @JsonProperty("NAO")
    private Long quantidadeNao;

    public ResultadoVotacaoResponse() {
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

    public ResultadoVotacaoResponse(String status, Long quantidadeSim, Long quantidadeNao) {
        this.status = status;
        this.quantidadeSim = quantidadeSim;
        this.quantidadeNao = quantidadeNao;
    }

    public static ResultadoVotacaoResponse of(String status, long quantidadeSim, long quantidadeNao) {
        return new ResultadoVotacaoResponse(status,quantidadeSim,quantidadeNao);
    }
}
