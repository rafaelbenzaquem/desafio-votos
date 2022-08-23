package br.com.benzaquem.desafiovotos.voto;

public enum OpcaoVoto {
    SIM("SIM"),NAO("NAO");

    private String value;

    private OpcaoVoto(String value) {
        this.value = value;
    }

    public static OpcaoVoto of(String value){
        if(value.equalsIgnoreCase("NAO")||value.equalsIgnoreCase("NÃO"))
            return OpcaoVoto.NAO;
        if(value.equalsIgnoreCase("SIM"))
            return OpcaoVoto.SIM;
            throw new IllegalArgumentException("Opção de voto inválida");
    }
}
