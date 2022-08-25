package br.com.benzaquem.desafiovotos.pauta;

import javax.validation.constraints.NotBlank;

public record PautaRequest(@NotBlank(message = "Campo não pode ser vazio ou em branco.") String nome) {

    public Pauta toModel() {
        return new Pauta(null, nome);
    }

}
