package br.com.benzaquem.desafiovotos.sessao;


import br.com.benzaquem.desafiovotos.commons.mensagem.MensagemResponseError;
import br.com.benzaquem.desafiovotos.pauta.PautaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1/sessoes")
public class SessaoVotacaoV1Controller {

    private SessaoVotacaoRepository sessaoVotacaoRepository;

    private PautaRepository pautaRepository;

    @Operation(summary = "Inicia uma nova sessão de votação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nova sessão foi iniciada com sucesso. ",
                    headers = @Header(name = "Location",
                            description = "Referência para nova nova sessão  iniciada. Ex.: ./v1/sessoes/{id_associado}")),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = {@Content(mediaType = "application/json;charset=UTF-8",
                            schema = @Schema(implementation = MensagemResponseError.class))})})
    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> abrirSessaoVotacao(@RequestBody @Valid SessaoRequest sessaoRequest) {
        log.info("Iniciando uma sessão de votação, request = {}", sessaoRequest);
        var idPauta = sessaoRequest.getIdPauta();
        var optPauta = pautaRepository.findById(idPauta);

        if (optPauta.isPresent()) {
            var sessao = sessaoRequest.toModel(optPauta.get());
            sessao = sessaoVotacaoRepository.save(sessao);
            log.info("Sessão iniciada com sucesso, sessão id = {}", sessao.getId());
            var uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/v1/sessoes/{id}").buildAndExpand(sessao.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        log.warn("Não foi possível iniciar sessão , não foi possível encotrar pauta id = {}", idPauta);
        MensagemResponseError errorResponse = new MensagemResponseError(HttpStatus.NOT_FOUND.value(), "Recurso não encontrado!", new ArrayList<>());
        errorResponse.addMensagemCampoError("id_pauta", String.format("Pauta id = %s não existe.", idPauta));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


}
