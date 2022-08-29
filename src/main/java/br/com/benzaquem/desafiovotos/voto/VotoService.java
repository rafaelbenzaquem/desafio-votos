package br.com.benzaquem.desafiovotos.voto;

import br.com.benzaquem.desafiovotos.analise.AnaliseCpfExternalService;
import br.com.benzaquem.desafiovotos.associado.AssociadoRepository;
import br.com.benzaquem.desafiovotos.commons.mensagem.Mensagem;
import br.com.benzaquem.desafiovotos.sessao.SessaoVotacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class VotoService {


    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private AnaliseCpfExternalService analiseCpfExternalService;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    private VotoRepository votoRepository;

    public Mensagem votarPauta(VotoRequest votoRequest, LocalDateTime horaDoVoto) {

        var idAssociado = votoRequest.getIdAssociado();
        var idPauta = votoRequest.getIdPauta();

        var optAssociado = associadoRepository.findById(idAssociado);
        var associado = optAssociado.orElseThrow(() -> {
            throw new IllegalArgumentException();
        });

        log.info("Iniciando analise do cpf do associado...");
        var analiseCpfResponse = analiseCpfExternalService.solicitarAnaliseVotoPorCpf(associado.getCpf());
        if (analiseCpfResponse.status().equals("UNABLE_TO_VOTE")) {
            log.warn("O associado id = {} não está habilitado para votar na pauta id = {}.", idAssociado, idPauta);
            return new Mensagem(HttpStatus.UNPROCESSABLE_ENTITY.value(), String.format("O associado id = %d não está habilitado para votar na pauta id = %d.", idAssociado, idPauta));
        }

        var optSessao = sessaoVotacaoRepository.buscarUltimaSessaoAbertaPorPauta(idPauta, horaDoVoto);
        if (optSessao.isPresent()) {
            if (votoRepository.findVotoByAssociadoAndPauta(idAssociado, idPauta).isPresent()) {
                log.warn("Não é possível votar mais de uma vez em uma mesma pauta. Associado - {} - pauta - {}", idAssociado, idPauta);
                return new Mensagem(HttpStatus.BAD_REQUEST.value(), "Não é possível votar mais de uma vez na mesma pauta.");
            }
            var sessao = optSessao.get();
            var voto = votoRepository.save(votoRequest.toModel(associado, sessao));
            log.info("Voto realizado com sucesso, voto = {}", voto);
            return new Mensagem(HttpStatus.OK.value(), "Seu voto foi computado com sucesso.");
        }
        log.warn("Não foi possível processar o seu voto, por não existir sessão aberta para pauta id= {}.", idPauta);
        return new Mensagem(HttpStatus.BAD_REQUEST.value(), String.format("Não foi possível processar o seu voto, por não existir sessão aberta para pauta id= %s.", idPauta));

    }
}
