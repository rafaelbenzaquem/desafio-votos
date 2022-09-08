package br.com.benzaquem.desafiovotos.resultado;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResultadoVotacaoResponse(@JsonProperty("id_pauta") Long idPauta,
                                       String status,
                                       @JsonProperty("SIM") Long quantidadeSim,
                                       @JsonProperty("NAO") Long quantidadeNao) {
    public static ResultadoVotacaoResponse of(Long idPauta, String status, long quantidadeSim, long quantidadeNao) {
        return new ResultadoVotacaoResponse(idPauta, status, quantidadeSim, quantidadeNao);
    }

    @Override
    public String toString() {
        return """
                {
                    "id_pauta":%d,
                    "status":"%s",
                    "SIM":%d,
                    "NAO":%d
                }
                """.formatted(idPauta, status, quantidadeSim, quantidadeNao);
    }
}
