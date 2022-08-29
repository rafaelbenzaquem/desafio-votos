package br.com.benzaquem.desafiovotos.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeCaseConverterTest {


    @Test
    void transformaCamelEmSnakeCAse() {
        var valor = "idAluno";

        String snake = SnakeCaseConverter.convert(valor);

        assertEquals("id_aluno", snake);

    }
}