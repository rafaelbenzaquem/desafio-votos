package br.com.benzaquem.desafiovotos.sessao;


import br.com.benzaquem.desafiovotos.pauta.PautaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/sessoes")
public class SessaoVotacaoController {

    private SessaoVotacaoRepository sessaoVotacaoRepository;

    private PautaRepository pautaRepository;

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> abrirSessaoVotacao(@RequestBody @Valid SessaoRequest sessaoRequest) {
        log.info("Iniciando uma sessão de votação, request = {}", sessaoRequest);
        var idPauta = sessaoRequest.getIdPauta();
        var optPauta = pautaRepository.findById(idPauta);

        if (optPauta.isPresent()) {
            var sessao = sessaoRequest.toModel(optPauta.get());
            sessao = sessaoVotacaoRepository.save(sessao);
            log.info("Sessão iniciada com sucesso, sessão = {}",sessao);
            var uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/sessoes/{id}").buildAndExpand(sessao.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        log.warn("Não foi possível iniciar sessão , não foi possível encotrar pauta id = {}", idPauta);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Pauta id = %s não existe", idPauta));
    }


}
