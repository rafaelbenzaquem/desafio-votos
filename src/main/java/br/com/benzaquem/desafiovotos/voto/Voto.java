package br.com.benzaquem.desafiovotos.voto;


import br.com.benzaquem.desafiovotos.associado.Associado;
import br.com.benzaquem.desafiovotos.sessao.Sessao;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Voto {

    @JsonIgnore
    @EmbeddedId
    private VotoPk id;

    private String opcao;

    public Voto() {
    }

    private Voto(VotoPk id, String opcao) {
        this.id = id;
        this.opcao = opcao;
    }

    public VotoPk getId() {
        return id;
    }

    public String getOpcao() {
        return opcao;
    }

    public static Voto of(Associado associado, Sessao sessao, String opcao){
        return new Voto(VotoPk.of(associado,sessao),opcao);
    }
}
