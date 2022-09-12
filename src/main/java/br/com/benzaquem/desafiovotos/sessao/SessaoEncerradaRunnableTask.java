package br.com.benzaquem.desafiovotos.sessao;

import br.com.benzaquem.desafiovotos.resultado.ResultadoVotacaoPublisher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessaoEncerradaRunnableTask implements Runnable {

    private final Long idPauta;

    private final ResultadoVotacaoPublisher resultadoVotacaoPublisher;

    public SessaoEncerradaRunnableTask(Long idPauta, ResultadoVotacaoPublisher resultadoVotacaoPublisher) {
        this.idPauta = idPauta;
        this.resultadoVotacaoPublisher = resultadoVotacaoPublisher;
    }

    @Override
    public void run() {
        resultadoVotacaoPublisher.pubResultadoVotacao(idPauta);
    }

}