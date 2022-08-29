package br.com.benzaquem.desafiovotos.voto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class VotoResultadoV1Controller {

    @Autowired
    private VotoResultadoService votoResultadoService;

    @GetMapping(value = "/v1/voto-resultado/pautas/{id_pauta}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<VotoResultadoResponse> calcularResultadoVotacao(@PathVariable("id_pauta") Long idPauta) {

        log.info("Apurando votos para a pauta id = {}", idPauta);

        VotoResultadoResponse votoResultadoResponse = votoResultadoService.apurarVotos(idPauta);

        return ResponseEntity.ok(votoResultadoResponse);
    }

}
