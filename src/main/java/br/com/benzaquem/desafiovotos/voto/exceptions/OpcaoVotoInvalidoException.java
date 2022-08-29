package br.com.benzaquem.desafiovotos.voto.exceptions;

import lombok.Getter;

@Getter
public class OpcaoVotoInvalidoException extends RuntimeException {

    private final String valor;

    public OpcaoVotoInvalidoException(String message, String valor) {
        super(message);
        this.valor = valor;
    }

}
