package br.com.benzaquem.desafiovotos.commons.mensagem;

import lombok.Builder;

@Builder
public class FieldMessage {

    private String campo;
    private String mensagem;

    public FieldMessage() {
    }

    public FieldMessage(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
