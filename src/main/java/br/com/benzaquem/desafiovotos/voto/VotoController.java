package br.com.benzaquem.desafiovotos.voto;

import br.com.benzaquem.desafiovotos.associado.AssociadoRepository;
import br.com.benzaquem.desafiovotos.pauta.PautaRepository;
import br.com.benzaquem.desafiovotos.sessao.SessaoVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping
public class VotoController {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    private VotoRepository votoRepository;


    @PostMapping("/votos")
    public ResponseEntity<VotoResponse> votarPauta(@RequestBody VotoRequest votoRequest) {
        var instant = LocalDateTime.now();
        var optAssociado = associadoRepository.findById(votoRequest.getIdAssociado());
        var optPauta = pautaRepository.findById(votoRequest.getIdPauta());
        var pauta = optPauta.get();

        var associado = optAssociado.get();

        var optSessao = sessaoVotacaoRepository.buscarUltimaSessaoAbertaPorPauta(votoRequest.getIdPauta(), instant);

        if (optSessao.isPresent()) {
            var sessao = optSessao.get();
            votoRepository.save(votoRequest.toModel(associado, sessao));
            return ResponseEntity.ok(new VotoResponse("Seu voto foi computado com sucesso."));
        }
        return ResponseEntity.unprocessableEntity().body(
                new VotoResponse("Não foi possível computar o seu voto, por não haver sessão aberta para pauta id=" + pauta.getId()));
    }

}
