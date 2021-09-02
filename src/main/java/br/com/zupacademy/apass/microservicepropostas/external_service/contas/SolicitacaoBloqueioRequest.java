package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import br.com.zupacademy.apass.microservicepropostas.cartao.bloqueio.Bloqueio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitacaoBloqueioRequest {

    @NotNull
    private Bloqueio bloqueio;

    /**
     *
     * @param bloqueio
     */
    public SolicitacaoBloqueioRequest(@NotNull Bloqueio bloqueio  ) {
        this.bloqueio = bloqueio;
    }

    @NotBlank
    public String getSistemaResponsavel() {
        return this.bloqueio.getSistemaResponsavel();
    }
}
