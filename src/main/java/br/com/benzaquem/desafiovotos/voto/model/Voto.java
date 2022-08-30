package br.com.benzaquem.desafiovotos.voto.model;


import br.com.benzaquem.desafiovotos.associado.Associado;
import br.com.benzaquem.desafiovotos.sessao.Sessao;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Voto {

    @JsonIgnore
    @EmbeddedId
    private VotoPk id;

    @Enumerated(EnumType.STRING)
    private OpcaoVoto opcao;

    @Column(name = "hora_do_voto")
    private LocalDateTime horaDoVoto;

    public Voto() {
    }

    public Voto(VotoPk id, OpcaoVoto opcao, LocalDateTime horaDoVoto) {
        this.id = id;
        this.opcao = opcao;
        this.horaDoVoto = horaDoVoto;
    }

    public VotoPk getId() {
        return id;
    }

    public OpcaoVoto getOpcao() {
        return opcao;
    }

    public LocalDateTime getHoraDoVoto() {
        return horaDoVoto;
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

    public static Voto of(Associado associado, Sessao sessao, OpcaoVoto opcao, LocalDateTime horaDoVoto) {
        return new Voto(VotoPk.of(associado, sessao), opcao, horaDoVoto);
    }

    @Override
    public String toString() {
        return "Voto{" +
                "id=" + id +
                ", opcao=" + getOpcao() +
                ", horaDoVoto=" + getHoraDoVoto() +
                '}';
    }
}
