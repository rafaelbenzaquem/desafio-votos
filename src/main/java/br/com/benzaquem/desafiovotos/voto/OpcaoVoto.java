package br.com.benzaquem.desafiovotos.voto;

import br.com.benzaquem.desafiovotos.voto.excep.OpcaoVotoInvalidoException;

public enum OpcaoVoto {
    SIM("SIM"), NAO("NAO");

    private final String value;

    OpcaoVoto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OpcaoVoto of(String valor) {
        if (valor.equalsIgnoreCase("NAO") || valor.equalsIgnoreCase("NÃO"))
            return OpcaoVoto.NAO;
        if (valor.equalsIgnoreCase("SIM"))
            return OpcaoVoto.SIM;
        throw new OpcaoVotoInvalidoException("Opção de voto inválida!", valor);
    }
}
