package br.com.zupacademy.apass.microservicepropostas.cartao.renegociacao;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoWrapper {
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
     *
     * @param id
     * @param quantidade
     * @param valor
     * @param dataDeCriacao
     */
    public RenegociacaoWrapper(@NotBlank String id,
                               @NotNull @Positive Integer quantidade,
                               @NotNull @Positive BigDecimal valor,
                               @NotNull LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    /**
     *
     * @param cartao
     * @return
     */
    public Renegociacao converte(Cartao cartao) {
        return new Renegociacao(cartao, this.id, this.quantidade, this.valor, this.dataDeCriacao);
    }
}
