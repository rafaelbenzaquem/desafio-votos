package br.com.benzaquem.desafiovotos.commons;

import br.com.benzaquem.desafiovotos.associado.exceptions.DocumentoDuplicadoException;
import br.com.benzaquem.desafiovotos.commons.mensagem.MensagemErroDeValidacaoResponse;
import br.com.benzaquem.desafiovotos.commons.mensagem.MensagemErroResponse;
import br.com.benzaquem.desafiovotos.commons.util.SnakeCaseConverter;
import feign.FeignException;
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
    public ResponseEntity<MensagemErroDeValidacaoResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        MensagemErroDeValidacaoResponse errorResponse = new MensagemErroDeValidacaoResponse(HttpStatus.BAD_REQUEST.value(), "Erro de validação!", new ArrayList<>());
        ex.getBindingResult().getFieldErrors().forEach(f -> errorResponse.addMensagemCampoError(SnakeCaseConverter.convert(f.getField()), f.getDefaultMessage()));
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(errorResponse);
    }


    @ExceptionHandler(DocumentoDuplicadoException.class)
    public ResponseEntity<MensagemErroDeValidacaoResponse> documentoDuplicadoExceptionHandler(DocumentoDuplicadoException ex) {
        MensagemErroDeValidacaoResponse errorResponse = new MensagemErroDeValidacaoResponse(HttpStatus.BAD_REQUEST.value(), "Erro de validação!", new ArrayList<>());
        errorResponse.addMensagemCampoError("cpf", ex.getMessage());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(errorResponse);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<MensagemErroResponse> servicoExterExceptionHandler(FeignException ex) {
        var errorResponse = new MensagemErroResponse(ex.status(), "Erro de serviço externo!", ex.getMessage());

        return ResponseEntity.status(ex.status()).contentType(MediaType.APPLICATION_JSON_UTF8).body(errorResponse);
    }

}
