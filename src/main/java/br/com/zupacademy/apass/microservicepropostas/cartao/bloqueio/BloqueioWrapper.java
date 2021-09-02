package br.com.zupacademy.apass.microservicepropostas.cartao.bloqueio;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class BloqueioWrapper {

    @NotNull
    private String identificador;

    @NotNull
    private LocalDateTime bloqueadoEm;

    @NotNull
    private String sistemaResponsavel;

    @NotNull
    private Boolean ativo;

    private String userAgent;

    private String ip;

    /**
     *
     * @param identificador
     * @param bloqueadoEm
     * @param sistemaResponsavel
     * @param ativo
     */
    public BloqueioWrapper(@NotBlank String identificador,
                           @NotNull LocalDateTime bloqueadoEm,
                           @NotBlank String sistemaResponsavel,
                           @NotNull Boolean ativo) {
        this.identificador = identificador;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    /**
     *
     * @param sistemaResponsavel
     * @param userAgent
     * @param ip
     */
    public BloqueioWrapper(String sistemaResponsavel,
                           String userAgent,
                           String ip) {

        this(UUID.randomUUID().toString(), LocalDateTime.now(), sistemaResponsavel, true);
        this.userAgent = userAgent;
        this.ip = ip;
    }

    /**
     *
     * @param cartao
     * @return
     */
    public Bloqueio converte(Cartao cartao) {
        return new Bloqueio(cartao,
                this.identificador,
                this.bloqueadoEm,
                this.sistemaResponsavel,
                this.ativo,
                Optional.of(this.userAgent),
                Optional.of(this.ip));
    }
}
