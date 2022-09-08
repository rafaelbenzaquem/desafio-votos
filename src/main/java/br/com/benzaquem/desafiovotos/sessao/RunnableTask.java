package br.com.benzaquem.desafiovotos.sessao;

import br.com.benzaquem.desafiovotos.resultado.ResultadoVotacaoService;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class RunnableTask implements Runnable {

    private final Long idPauta;
    private final ResultadoVotacaoService resultadoVotacaoService;

    public RunnableTask(Long idPauta, ResultadoVotacaoService resultadoVotacaoService) {
        this.idPauta = idPauta;
        this.resultadoVotacaoService = resultadoVotacaoService;
    }

    @Override
    public void run() {
        var resultadoVotacaoResponse = resultadoVotacaoService.apurarVotos(idPauta);
        log.info(" {} Runnable Task with \n{}",new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), resultadoVotacaoResponse);
    }
}