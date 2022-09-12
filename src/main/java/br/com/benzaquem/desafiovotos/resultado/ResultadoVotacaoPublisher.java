package br.com.benzaquem.desafiovotos.resultado;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class ResultadoVotacaoPublisher {

    private final ResultadoVotacaoService resultadoVotacaoService;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final String topico;

    public ResultadoVotacaoPublisher(ResultadoVotacaoService resultadoVotacaoService,
                                     KafkaTemplate<String, String> kafkaTemplate,
                                     @Value("${topico.nome}") String topico) {

        this.resultadoVotacaoService = resultadoVotacaoService;
        this.kafkaTemplate = kafkaTemplate;
        this.topico = topico;
    }

    public void pubResultadoVotacao(Long idPauta) {
        var resultadoVotacaoResponse = resultadoVotacaoService.apurarVotos(idPauta);
        log.info("Às {} a sessão foi encerrada, o resultado será enviado para o tópico {} : \n{}", topico, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), resultadoVotacaoResponse);
        kafkaTemplate.send(topico, resultadoVotacaoResponse.toString());
    }

}
