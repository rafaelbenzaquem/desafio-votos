package br.com.benzaquem.desafiovotos.voto;

import br.com.benzaquem.desafiovotos.sessao.Sessao;
import br.com.benzaquem.desafiovotos.sessao.SessaoVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VotoResultadoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    public VotoResultadoResponse apurarVotos(Long idPauta) {

        var list =votoRepository.findVotosPorPauta(idPauta);
        var qtSIM = list.stream().filter(s -> s.equalsIgnoreCase("sim")).count();
        var qtNAO = list.stream().filter(s -> s.equalsIgnoreCase("nao")).count();
        var sessao = sessaoVotacaoRepository.buscarUltimaSessaoAbertaPorPauta(idPauta, LocalDateTime.now());

        var status = sessao.isPresent()?"resultado parcial":"resultado final";

        return VotoResultadoResponse.of(status,qtSIM,qtNAO);
    }
}
