package br.com.benzaquem.desafiovotos.sessao;

import br.com.benzaquem.desafiovotos.pauta.Pauta;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class SessaoRequest {

    @NotBlank(message = "Campo não pode ser vazio ou em branco.")
    private String nome;

    @Min(value = 1,message = "Duração mínima de uma sessão é 1 minuto.")
    @JsonProperty(value = "duracao")
    private Long duracao;

    @JsonProperty(value = "id_pauta")
    private Long idPauta;

    public SessaoRequest() {
    }

    public String getNome() {
        return nome;
    }


    public Long getDuracao() {
        return duracao;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public Sessao toModel(Pauta pauta) {
        duracao = duracao == null ? 1 : duracao;
        var dataInicial = LocalDateTime.now();
        var dataFinal = dataInicial.plusMinutes(duracao);
        return new Sessao(null, nome, dataInicial, dataFinal, pauta);
    }

    @Override
    public String toString() {
        return "SessaoRequest{" +
                "nome='" + nome + '\'' +
                ", duracao=" + duracao +
                ", idPauta=" + idPauta +
                '}';
    }
}
