package br.com.zupacademy.apass.microservicepropostas.external_service.contas.renegociacao;

import br.com.zupacademy.apass.microservicepropostas.cartao.renegociacao.RenegociacaoWrapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoResponse {

    @NotBlank
    private String id;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotBlank
    @Positive
    private BigDecimal valor;

    @NotNull
    private LocalDateTime dataDeCriacao;

    /**
     * Construtor com dados obrigatórios.
     * @param id
     * @param quantidade
     * @param valor
     * @param dataDeCriacao
     */
    public RenegociacaoResponse(@NotBlank String id,
                                @NotNull @Positive Integer quantidade,
                                @NotNull @Positive BigDecimal valor,
                                @NotNull LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    /**
     * Converte os dados para um objeto de domínio.
     *
     * @return
     */
    public RenegociacaoWrapper converte() {
        return new RenegociacaoWrapper(this.id, this.quantidade, this.valor, this.dataDeCriacao);
    }
}
