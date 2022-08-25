package br.com.benzaquem.desafiovotos.associado;


import br.com.benzaquem.desafiovotos.associado.exceptions.DocumentoDuplicadoException;
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
public class AssociadoCadastroController {

    @Autowired
    private AssociadoRepository associadoRepository;

    @PostMapping(value = "/associados", produces = "application/json;charset=UTF-8")
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
