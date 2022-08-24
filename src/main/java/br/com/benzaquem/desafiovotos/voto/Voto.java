package br.com.benzaquem.desafiovotos.voto;


import br.com.benzaquem.desafiovotos.associado.Associado;
import br.com.benzaquem.desafiovotos.sessao.Sessao;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Voto {

    @JsonIgnore
    @EmbeddedId
    private VotoPk id;

    @Enumerated(EnumType.STRING)
    private OpcaoVoto opcao;

    public Voto() {
    }

    private Voto(VotoPk id, OpcaoVoto opcao) {
        this.id = id;
        this.opcao = opcao;
    }

    public VotoPk getId() {
        return id;
    }

    public OpcaoVoto getOpcao() {
        return opcao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        return Objects.equals(id, voto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static Voto of(Associado associado, Sessao sessao, OpcaoVoto opcao){
        return new Voto(VotoPk.of(associado,sessao),opcao);
    }

    @Override
    public String toString() {
        return "Voto{"+ id +
                ", opcao=" + opcao +
                '}';
    }
}
