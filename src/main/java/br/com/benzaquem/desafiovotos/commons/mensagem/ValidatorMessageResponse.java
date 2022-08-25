package br.com.benzaquem.desafiovotos.commons.mensagem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidatorMessageResponse extends MessageResponse {

    private final List<FieldMessage> campos;

    public ValidatorMessageResponse(Integer status, String message, LocalDateTime instant) {
        super(status, message, instant);
        campos = new ArrayList<>();
    }

    public List<FieldMessage> getCampos() {
        return List.copyOf(campos);
    }

    public void addFieldError(String field, String message){
        campos.add(new FieldMessage(field,message));
    }

}
