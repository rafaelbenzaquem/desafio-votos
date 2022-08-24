package br.com.benzaquem.desafiovotos.associado;

import static br.com.benzaquem.desafiovotos.util.OfuscaDadosUtil.*;

public class AssociadoResquest {

    private String nome;

    private String cpf;

    public AssociadoResquest() {
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Associado toModel(){
        return new Associado(null, nome, cpf);
    }

    @Override
    public String toString() {
        return "AssociadoResquest{" +
                "nome='" + nome + '\'' +
                ", cpf='" + ofuscaCpf(cpf) + '\'' +
                '}';
    }
}
