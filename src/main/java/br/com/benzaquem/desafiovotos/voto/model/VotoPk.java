package br.com.benzaquem.desafiovotos.voto.model;

import br.com.benzaquem.desafiovotos.associado.Associado;
import br.com.benzaquem.desafiovotos.sessao.Sessao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class VotoPk implements Serializable {

    private static final long serialVersionUID = 4022885212288514238L;
    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;
    @ManyToOne
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    public VotoPk() {
    }

    public VotoPk(Associado associado, Sessao sessao) {
        this.associado = associado;
        this.sessao = sessao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotoPk votoPk = (VotoPk) o;
        return Objects.equals(associado.getId(), votoPk.associado.getId()) &&
                Objects.equals(sessao.getId(), votoPk.sessao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(associado.getId(), sessao.getId());
    }

    public static VotoPk of(Associado associado, Sessao sessao) {
        return new VotoPk(associado, sessao);
    }

    @Override
    public String toString() {
        return "idAssociado=" + associado.getId() +
                ", idSessao=" + sessao.getId();
    }
}
