package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import br.com.zupacademy.apass.microservicepropostas.cartao.BloqueioWrapper;
import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BloqueioResponse {

    @NotBlank
    private String id;

    @NotNull
    private LocalDateTime bloqueadoEm;

    @NotBlank
    private String sistemaResponsavel;

    @NotNull
    private Boolean ativo;

    public BloqueioResponse(@NotBlank String id,
                            @NotNull LocalDateTime bloqueadoEm,
                            @NotBlank String sistemaResponsavel,
                            @NotNull Boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public BloqueioWrapper converte() {
        return new BloqueioWrapper(this.id, this.bloqueadoEm, this.sistemaResponsavel, this.ativo);
    }
}
