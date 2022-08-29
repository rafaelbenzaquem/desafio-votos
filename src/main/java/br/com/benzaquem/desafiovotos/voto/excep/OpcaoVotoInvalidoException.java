package br.com.benzaquem.desafiovotos.voto.excep;

public class OpcaoVotoInvalidoException extends RuntimeException {


    private final String valor;

    public OpcaoVotoInvalidoException(String message, String valor) {
        super(message);
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
