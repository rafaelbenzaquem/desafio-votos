package br.com.benzaquem.desafiovotos.commons.mensagem;

import java.time.LocalDateTime;

public class MessageResponse {

    private final Integer status;
    private final String mensagem;
    private final LocalDateTime instant;

    public MessageResponse(Integer status, String mensagem, LocalDateTime instant) {
        this.status = status;
        this.mensagem = mensagem;
        this.instant = instant;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getInstant() {
        return instant;
    }
}
