package br.com.benzaquem.desafiovotos.commons;

import br.com.benzaquem.desafiovotos.associado.exceptions.DocumentoDuplicadoException;
import br.com.benzaquem.desafiovotos.commons.mensagem.MensagemCampoError;
import br.com.benzaquem.desafiovotos.commons.mensagem.MensagemValidatorResponse;
import br.com.benzaquem.desafiovotos.commons.util.SnakeCaseConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemValidatorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        MensagemValidatorResponse errorResponse = new MensagemValidatorResponse(HttpStatus.BAD_REQUEST.value(), "Erro de validação!", new ArrayList<>());
        ex.getBindingResult().getFieldErrors().forEach(f -> errorResponse.addMensagemCampoError(SnakeCaseConverter.convert(f.getField()), f.getDefaultMessage()));
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(errorResponse);
    }


    @ExceptionHandler(DocumentoDuplicadoException.class)
    public ResponseEntity<MensagemCampoError> documentoDuplicadoExceptionHandler(DocumentoDuplicadoException ex) {

        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(MensagemCampoError.builder()
                .campo("cpf")
                .mensagem(ex.getMessage())
                .build());
    }

}
