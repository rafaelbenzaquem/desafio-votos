package br.com.benzaquem.desafiovotos.sessao;


import br.com.benzaquem.desafiovotos.pauta.PautaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/sessoes")
public class SessaoVotacaoController {

    private SessaoVotacaoRepository sessaoVotacaoRepository;

    private PautaRepository pautaRepository;

    @PostMapping
    public ResponseEntity<?> abrirSessaoVotacao(@RequestBody SessaoRequest sessaoRequest) {
        var idPauta = sessaoRequest.getIdPauta();
        var optPauta = pautaRepository.findById(idPauta);

        if (optPauta.isPresent()) {

            var sessao = sessaoRequest.toModel(optPauta.get());
            sessao = sessaoVotacaoRepository.save(sessao);
            var uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/sessoes/{id}").buildAndExpand(sessao.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        log.warn("Pauta id = {} não existe", idPauta);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Pauta id = %s não existe", idPauta));
    }


}
