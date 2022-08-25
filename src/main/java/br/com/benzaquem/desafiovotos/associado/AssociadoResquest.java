package br.com.benzaquem.desafiovotos.associado;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

import static br.com.benzaquem.desafiovotos.commons.util.OfuscaDadosUtil.*;

public class AssociadoResquest {

    @NotBlank(message = "Campo não pode ser vazio ou em branco.")
    private String nome;

    @CPF(message = "Documento informado inválido.")
    private String cpf;

    public AssociadoResquest() {
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Associado toModel() {
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
