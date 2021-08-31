package br.com.zupacademy.apass.microservicepropostas.cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ParcelaWrapper {
    @NotBlank
    private String identificador;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @Positive
    private BigDecimal valor;

    /**
     *
     * @param identificador
     * @param quantidade
     * @param valor
     */
    public ParcelaWrapper(@NotBlank String identificador,
                          @NotNull @Positive Integer quantidade,
                          @NotNull @Positive BigDecimal valor) {
        this.identificador = identificador;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Parcela converte(Cartao cartao) {
        return new Parcela(cartao, this.identificador, this.quantidade, this.valor);
    }
}
