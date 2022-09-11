package br.com.benzaquem.desafiovotos.commons.mensagem;

public record MensagemErroResponse(Integer status, String erro, String mensagem) {
}
