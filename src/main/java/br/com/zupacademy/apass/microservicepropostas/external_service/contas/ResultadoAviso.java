package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class ResultadoAviso {
    @NotBlank
    private String resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResultadoAviso(@NotBlank String resultado) {
        this.resultado = resultado;
    }
}
