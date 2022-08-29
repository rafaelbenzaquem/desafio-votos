package br.com.benzaquem.desafiovotos.pauta;


import br.com.benzaquem.desafiovotos.commons.mensagem.MensagemResponseError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
public class PautaCadastroV1Controller {

    private PautaRepository pautaRepository;


    @Operation(summary = "Cria uma nova pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo pauta foi criada com sucesso. ",
                    headers = @Header(name = "Location",
                            description = "Referência para nova pauta criada. Ex.: ./v1/pautas/{id_pauta}")),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = {@Content(mediaType = "application/json;charset=UTF-8",
                            schema = @Schema(implementation = MensagemResponseError.class))})})
    @PostMapping(value = "/v1/pautas", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Void> cadastrarPauta(@RequestBody @Valid PautaRequest pautaRequest) {
        log.info("Iniciando cadastro de uma pauta, request = {}", pautaRequest);
        var pauta = pautaRequest.toModel();
        pauta = pautaRepository.save(pauta);
        log.info("Pauta cadastrada com sucesso, pauta = {}", pauta);
        var uriResponse = ServletUriComponentsBuilder.fromCurrentContextPath().path("/v1/pautas/{id}").buildAndExpand(pauta.getId()).toUri();
        return ResponseEntity.created(uriResponse).build();
    }


}
