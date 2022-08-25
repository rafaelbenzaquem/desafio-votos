package br.com.benzaquem.desafiovotos.voto;

import br.com.benzaquem.desafiovotos.associado.Associado;
import br.com.benzaquem.desafiovotos.sessao.Sessao;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VotoRequest {

    @JsonProperty("id_associado")
    private Long idAssociado;

    @JsonProperty("id_pauta")
    private Long idPauta;

    private String opcao;

    public VotoRequest() {
    }

    public Long getIdAssociado() {
        return idAssociado;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public String getOpcao() {
        return opcao;
    }

    public Voto toModel(Associado associado, Sessao sessao) {
        return Voto.of(associado, sessao, OpcaoVoto.of(opcao));
    }
}
