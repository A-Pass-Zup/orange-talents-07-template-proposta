package br.com.zupacademy.apass.microservicepropostas.external_service.contas.carteira_digital;

import javax.validation.constraints.NotBlank;

public class ResultadoCarteiraResponse {

    @NotBlank
    private String resultado;

    @NotBlank
    private String id;

    public ResultadoCarteiraResponse(@NotBlank String resultado, @NotBlank String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
