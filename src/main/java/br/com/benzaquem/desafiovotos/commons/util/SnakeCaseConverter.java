package br.com.benzaquem.desafiovotos.commons.util;

public class SnakeCaseConverter {
    private SnakeCaseConverter() {
    }

    public static String convert(String str) {
        return str.replaceAll("([a-z])([A-Z]+)", "$1_$2")
                .toLowerCase();
    }

}
