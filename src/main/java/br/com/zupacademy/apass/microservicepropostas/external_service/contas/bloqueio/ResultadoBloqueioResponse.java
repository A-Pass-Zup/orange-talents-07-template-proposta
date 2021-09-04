package br.com.zupacademy.apass.microservicepropostas.external_service.contas.bloqueio;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class ResultadoBloqueioResponse {
    @NotBlank
    private String resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResultadoBloqueioResponse(@NotBlank String resultado) {
        this.resultado = resultado;
    }
}
