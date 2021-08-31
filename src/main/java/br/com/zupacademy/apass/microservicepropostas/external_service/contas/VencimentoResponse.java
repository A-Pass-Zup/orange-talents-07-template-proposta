package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class VencimentoResponse {

    @NotBlank
    private String id;

    @NotNull
    @Positive
    private Integer dia;

    @NotNull
    private LocalDateTime dataDeCriacao;

    /**
     * Construtor com dados obrigatórios.
     *
     * @param id
     * @param dia
     * @param dataDeCriacao
     */
    public VencimentoResponse(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }
}
