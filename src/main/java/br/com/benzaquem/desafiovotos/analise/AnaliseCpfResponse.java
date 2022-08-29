package br.com.benzaquem.desafiovotos.analise;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AnaliseCpfResponse(@JsonProperty("status")String status) {
}
