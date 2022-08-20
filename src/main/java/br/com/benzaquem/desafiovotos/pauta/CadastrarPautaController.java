package br.com.benzaquem.desafiovotos.pauta;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping
@AllArgsConstructor
public class CadastrarPautaController {

    private PautaRepository pautaRepository;


    @PostMapping("/pautas")
    public ResponseEntity<Void> cadastrarPauta(@RequestBody PautaRequest pautaRequest) {
        var pauta = pautaRequest.toModel();
        pauta = pautaRepository.save(pauta);
        var uriResponse = ServletUriComponentsBuilder.fromCurrentContextPath().path("/pautas/{id}").buildAndExpand(pauta.getId()).toUri();
        return ResponseEntity.created(uriResponse).build();
    }


}
