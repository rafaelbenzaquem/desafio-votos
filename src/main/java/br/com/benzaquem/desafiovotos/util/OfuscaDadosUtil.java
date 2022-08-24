package br.com.benzaquem.desafiovotos.util;

public class OfuscaDadosUtil {

    private OfuscaDadosUtil(){}
    public static String ofuscaCpf(String cpf){
        return cpf.substring(0,3)+".***.***-**";
    }
}
