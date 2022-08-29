package br.com.benzaquem.desafiovotos.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${analise.voto.url}", name = "avaliacao-financeira")
public interface AnaliseCpfExternalService {


    @GetMapping(value = "/{cpf}",produces = "application/json")
    AnaliseCpfResponse solicitarAnaliseVotoPorCpf(@RequestParam("cpf") String cpf);
}
