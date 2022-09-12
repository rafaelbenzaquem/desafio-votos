package br.com.benzaquem.desafiovotos.resultado;

import br.com.benzaquem.desafiovotos.sessao.SessaoVotacaoRepository;
import br.com.benzaquem.desafiovotos.voto.VotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ResultadoVotacaoService {

    private VotoRepository votoRepository;

    private SessaoVotacaoRepository sessaoVotacaoRepository;

    public ResultadoVotacaoResponse apurarVotos(Long idPauta) {

        var list = votoRepository.findVotosPorPauta(idPauta);
        var qtSIM = list.stream().filter(s -> s.equalsIgnoreCase("sim")).count();
        var qtNAO = list.stream().filter(s -> s.equalsIgnoreCase("nao")).count();
        var sessao = sessaoVotacaoRepository.buscarUltimaSessaoAbertaPorPauta(idPauta, LocalDateTime.now());

        var status = sessao.isPresent() ? "resultado parcial" : "resultado final";

        return ResultadoVotacaoResponse.of(idPauta, status, qtSIM, qtNAO);
    }
}
