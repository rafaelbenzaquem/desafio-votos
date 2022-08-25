package br.com.benzaquem.desafiovotos.associado.exceptions;

public class DocumentoDuplicadoException extends RuntimeException {
    public DocumentoDuplicadoException(String mensagem) {
        super(mensagem);
    }
}
