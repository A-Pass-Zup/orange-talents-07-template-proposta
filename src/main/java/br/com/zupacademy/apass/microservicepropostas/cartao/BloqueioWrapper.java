package br.com.zupacademy.apass.microservicepropostas.cartao;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BloqueioWrapper {

    @NotNull
    private String identificador;

    @NotNull
    private LocalDateTime bloqueadoEm;

    @NotNull
    private String sistemaResponsavel;

    @NotNull
    private Boolean ativo;

    public BloqueioWrapper(@NotBlank String identificador,
                           @NotNull LocalDateTime bloqueadoEm,
                           @NotBlank String sistemaResponsavel,
                           @NotNull Boolean ativo) {
        this.identificador = identificador;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio converte(Cartao cartao) {
        return new Bloqueio(cartao, this.identificador, this.bloqueadoEm, this.sistemaResponsavel, this.ativo);
    }
}
