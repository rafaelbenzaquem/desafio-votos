package br.com.benzaquem.desafiovotos.resultado;

import br.com.benzaquem.desafiovotos.sessao.SessaoVotacaoRepository;
import br.com.benzaquem.desafiovotos.voto.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResultadoVotacaoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    public ResultadoVotacaoResponse apurarVotos(Long idPauta) {

        var list =votoRepository.findVotosPorPauta(idPauta);
        var qtSIM = list.stream().filter(s -> s.equalsIgnoreCase("sim")).count();
        var qtNAO = list.stream().filter(s -> s.equalsIgnoreCase("nao")).count();
        var sessao = sessaoVotacaoRepository.buscarUltimaSessaoAbertaPorPauta(idPauta, LocalDateTime.now());

        var status = sessao.isPresent()?"resultado parcial":"resultado final";

        return ResultadoVotacaoResponse.of(status,qtSIM,qtNAO);
    }
}
