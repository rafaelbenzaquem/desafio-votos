package br.com.benzaquem.desafiovotos.voto;

import br.com.benzaquem.desafiovotos.commons.mensagem.Mensagem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/v1")
public class VotoV1Controller {

    @Autowired
    private VotoService votoService;

    @PostMapping(value = "/votos", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Mensagem> votarPauta(@RequestBody @Valid VotoRequest votoRequest) {
        log.info("Iniciando processamento de voto, request = {}", votoRequest);
        var horaDoVoto = LocalDateTime.now();

        Mensagem resposta = votoService.votarPauta(votoRequest, horaDoVoto);

        return ResponseEntity.status(resposta.status()).body(resposta);
    }

}
