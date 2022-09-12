package br.com.benzaquem.desafiovotos.sessao;


import br.com.benzaquem.desafiovotos.commons.mensagem.MensagemErroDeValidacaoResponse;
import br.com.benzaquem.desafiovotos.pauta.PautaRepository;
import br.com.benzaquem.desafiovotos.resultado.ResultadoVotacaoPublisher;
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
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1/sessoes")
public class SessaoVotacaoV1Controller {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    private final PautaRepository pautaRepository;

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private final ResultadoVotacaoPublisher resultadoVotacaoPublisher;


    @Operation(summary = "Inicia uma nova sessão de votação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nova sessão foi iniciada com sucesso. ",
                    headers = @Header(name = "Location",
                            description = "Referência para nova nova sessão  iniciada. Ex.: ./v1/sessoes/{id_associado}")),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = {@Content(mediaType = "application/json;charset=UTF-8",
                            schema = @Schema(implementation = MensagemErroDeValidacaoResponse.class))})})
    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> abrirSessaoVotacao(@RequestBody @Valid SessaoRequest sessaoRequest) {
        log.info("Iniciando uma sessão de votação, request = {}", sessaoRequest);
        var idPauta = sessaoRequest.getIdPauta();
        var optPauta = pautaRepository.findById(idPauta);

        if (optPauta.isPresent()) {
            var pauta = optPauta.get();
            var sessao = sessaoRequest.toModel(pauta);
            sessao = sessaoVotacaoRepository.save(sessao);

            var ldt = sessao.getFim();
            var date = Date.from(Timestamp.valueOf(ldt).toInstant());
            log.info("Agendando evento de encerramento da sessão id = {} para {}", sessao.getId(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date));
            threadPoolTaskScheduler.schedule(new SessaoEncerradaRunnableTask(pauta.getId(), resultadoVotacaoPublisher), date);

            log.info("Sessão iniciada com sucesso, sessão id = {}", sessao.getId());
            var uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/v1/sessoes/{id}").buildAndExpand(sessao.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        log.warn("Não foi possível iniciar sessão , não foi possível encotrar pauta id = {}", idPauta);
        MensagemErroDeValidacaoResponse errorResponse = new MensagemErroDeValidacaoResponse(HttpStatus.NOT_FOUND.value(), "Recurso não encontrado!", new ArrayList<>());
        errorResponse.addMensagemCampoError("id_pauta", String.format("Pauta id = %s não existe.", idPauta));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


}
