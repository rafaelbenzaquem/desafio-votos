package br.com.benzaquem.desafiovotos.associado;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping
public class AssociadoCadastroController {

    @Autowired
    private AssociadoRepository associadoRepository;

    @PostMapping("/associados")
    public ResponseEntity<Void> cadastrarAssociado(@RequestBody AssociadoResquest associadoResquest) {

        var associado = associadoResquest.toModel();
        associado = associadoRepository.save(associado);
        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
                path("/{id}").buildAndExpand(associado.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}
