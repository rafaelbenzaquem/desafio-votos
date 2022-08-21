package br.com.benzaquem.desafiovotos.associado;

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
}
