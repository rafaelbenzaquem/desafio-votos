package br.com.benzaquem.desafiovotos.commons.util;

import br.com.benzaquem.desafiovotos.commons.util.OfuscaDadosUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfuscaDadosUtilTest {

    @Test
    void ofuscaCpfTest(){
        assertEquals("123.***.***-**", OfuscaDadosUtil.ofuscaCpf("12345678900"));
    }

}