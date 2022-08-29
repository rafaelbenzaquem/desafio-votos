package br.com.benzaquem.desafiovotos.associado;


import br.com.benzaquem.desafiovotos.associado.exceptions.DocumentoDuplicadoException;
import br.com.benzaquem.desafiovotos.commons.mensagem.MensagemResponse;
import br.com.benzaquem.desafiovotos.commons.mensagem.MensagemValidatorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import static br.com.benzaquem.desafiovotos.commons.util.OfuscaDadosUtil.ofuscaCpf;


@Slf4j
@RestController
@RequestMapping
public class AssociadoCadastroV1Controller {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Operation(summary = "Cria um novo associado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo associado foi criado com sucesso. ",
                    headers = @Header(name = "Location",
                            description = "Referência para novo associado criado. Ex.: ./v1/associados/{id_associado}")),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = {@Content(mediaType = "application/json;charset=UTF-8",
                            schema = @Schema(implementation = MensagemValidatorResponse.class))})})
    @PostMapping(value = "/v1/associados", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Void> cadastrarAssociado(@RequestBody @Valid AssociadoResquest associadoResquest) {
        log.info("Iniciando cadastro de associado, request = {}", associadoResquest);

        boolean existsCpf = associadoRepository.existsAssociadoByCpf(associadoResquest.getCpf());
        if (existsCpf) {
            log.warn("Documento já cadastrado, N° = " + ofuscaCpf(associadoResquest.getCpf()));
            throw new DocumentoDuplicadoException("Documento já cadastrado, N° = " + associadoResquest.getCpf());
        }

        var associado = associadoResquest.toModel();
        associado = associadoRepository.save(associado);
        log.info("Associado cadastrado com sucesso, associado = {}", associado);
        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
                path("/{id}").buildAndExpand(associado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
