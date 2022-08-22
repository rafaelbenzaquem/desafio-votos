package br.com.benzaquem.desafiovotos.voto;

import br.com.benzaquem.desafiovotos.associado.Associado;
import br.com.benzaquem.desafiovotos.sessao.Sessao;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

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

    private VotoPk(Associado associado, Sessao sessao) {
        this.associado = associado;
        this.sessao = sessao;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
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
}
