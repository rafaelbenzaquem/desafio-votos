package br.com.benzaquem.desafiovotos.voto;

import br.com.benzaquem.desafiovotos.associado.Associado;
import br.com.benzaquem.desafiovotos.associado.AssociadoRepository;
import br.com.benzaquem.desafiovotos.pauta.PautaRepository;
import br.com.benzaquem.desafiovotos.sessao.SessaoVotacaoRepository;
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
    private AssociadoRepository associadoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    private VotoRepository votoRepository;


    @PostMapping(value = "/votos", produces = "application/json;charset=UTF-8")
    public ResponseEntity<VotoResponse> votarPauta(@RequestBody @Valid VotoRequest votoRequest) {
        log.info("Iniciando processamento de voto, request = {}", votoRequest);
        var horaDoVoto = LocalDateTime.now();
        var idAssociado = votoRequest.getIdAssociado();
        var idPauta = votoRequest.getIdPauta();


        var optSessao = sessaoVotacaoRepository.buscarUltimaSessaoAbertaPorPauta(idPauta, horaDoVoto);
        if (optSessao.isPresent()) {
            if (votoRepository.findVotoByAssociadoAndPauta(idAssociado, idPauta).isPresent()) {
                log.warn("Não é possível votar mais de uma vez em uma mesma pauta. Associado - {} - pauta - {}", idAssociado, idPauta);
                return ResponseEntity.badRequest().body(new VotoResponse("Não é possível votar mais de uma vez na mesma pauta."));
            }
            var sessao = optSessao.get();
            var voto = votoRepository.save(votoRequest.toModel(new Associado(idAssociado, null, null), sessao));
            log.info("Voto realizado com sucesso, voto = {}", voto);
            return ResponseEntity.ok(new VotoResponse("Seu voto foi computado com sucesso."));
        }
        log.warn("Não foi possível processar o seu voto, por não existir sessão aberta para pauta id= {}.", idPauta);
        return ResponseEntity.badRequest().body(
                new VotoResponse(String.format("Não foi possível processar o seu voto, por não existir sessão aberta para pauta id= %s.", idPauta)));
    }


}
