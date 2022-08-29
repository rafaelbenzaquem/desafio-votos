package br.com.benzaquem.desafiovotos.voto;

import br.com.benzaquem.desafiovotos.associado.Associado;
import br.com.benzaquem.desafiovotos.commons.validadores.annotation.Enum;
import br.com.benzaquem.desafiovotos.commons.validadores.annotation.NotExistDB;
import br.com.benzaquem.desafiovotos.pauta.Pauta;
import br.com.benzaquem.desafiovotos.sessao.Sessao;
import br.com.benzaquem.desafiovotos.voto.model.OpcaoVoto;
import br.com.benzaquem.desafiovotos.voto.model.Voto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VotoRequest {


    @NotExistDB(domainClass = Associado.class, fieldName = "id")
    @JsonProperty("id_associado")
    private Long idAssociado;

    @NotExistDB(domainClass = Pauta.class, fieldName = "id")
    @JsonProperty("id_pauta")
    private Long idPauta;

    @Enum(message = "Opção escolhida é inválida, escolha['sim','não']",
            values = {"SIM", "NÃO", "NAO"},
            ignoreCase = true)
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
