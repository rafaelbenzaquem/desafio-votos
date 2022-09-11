package br.com.benzaquem.desafiovotos.commons.mensagem;

import java.util.List;

public record MensagemErroDeValidacaoResponse(Integer status, String mensagem, List<MensagemErroCampo> campos) {

    public void addMensagemCampoError(String campo, String message){
        campos.add(new MensagemErroCampo(campo,message));
    }

}
