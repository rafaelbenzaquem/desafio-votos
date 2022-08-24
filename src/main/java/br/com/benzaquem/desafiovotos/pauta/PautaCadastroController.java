package br.com.benzaquem.desafiovotos.pauta;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
public class PautaCadastroController {

    private PautaRepository pautaRepository;


    @PostMapping("/pautas")
    public ResponseEntity<Void> cadastrarPauta(@RequestBody PautaRequest pautaRequest) {
        log.info("Iniciando cadastro de uma pauta, request = {}", pautaRequest);
        var pauta = pautaRequest.toModel();
        pauta = pautaRepository.save(pauta);
        log.info("Pauta cadastrada com sucesso, pauta = {}", pauta);
        var uriResponse = ServletUriComponentsBuilder.fromCurrentContextPath().path("/pautas/{id}").buildAndExpand(pauta.getId()).toUri();
        return ResponseEntity.created(uriResponse).build();
    }


}
