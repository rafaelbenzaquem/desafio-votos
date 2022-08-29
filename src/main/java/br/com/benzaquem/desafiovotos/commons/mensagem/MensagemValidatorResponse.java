package br.com.benzaquem.desafiovotos.commons.mensagem;

import java.util.List;

public record MensagemValidatorResponse(Integer status, String mensagem, List<MensagemCampoError> campos) {

    public void addMensagemCampoError(String campo, String message){
        campos.add(new MensagemCampoError(campo,message));
    }

}
