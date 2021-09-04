package br.com.zupacademy.apass.microservicepropostas.external_service.contas.aviso_viagem;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class ResultadoAvisoViagemResponse {
    @NotBlank
    private String resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResultadoAvisoViagemResponse(@NotBlank String resultado) {
        this.resultado = resultado;
    }
}
