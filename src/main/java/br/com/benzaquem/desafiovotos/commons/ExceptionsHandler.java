package br.com.benzaquem.desafiovotos.commons;

import br.com.benzaquem.desafiovotos.associado.exceptions.DocumentoDuplicadoException;
import br.com.benzaquem.desafiovotos.commons.mensagem.FieldMessage;
import br.com.benzaquem.desafiovotos.commons.mensagem.ValidatorMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidatorMessageResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        ValidatorMessageResponse errorResponse = new ValidatorMessageResponse(HttpStatus.BAD_REQUEST.value(), "Erro de validação!", LocalDateTime.now());
        ex.getBindingResult().getFieldErrors().forEach(f -> errorResponse.addFieldError(f.getField(), f.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(DocumentoDuplicadoException.class)
    public ResponseEntity<FieldMessage> documentoDuplicadoExceptionHandler(DocumentoDuplicadoException ex) {

        return ResponseEntity.unprocessableEntity().body(FieldMessage.builder()
                .campo("cpf")
                .mensagem(ex.getMessage())
                .build());
    }

}
