package br.com.benzaquem.desafiovotos.commons.mensagem;

import lombok.Builder;

@Builder
public record MensagemCampoError(String campo, String mensagem) {
}
