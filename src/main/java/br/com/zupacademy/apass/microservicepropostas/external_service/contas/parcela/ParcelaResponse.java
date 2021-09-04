package br.com.zupacademy.apass.microservicepropostas.external_service.contas.parcela;

import br.com.zupacademy.apass.microservicepropostas.cartao.parcela.ParcelaWrapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ParcelaResponse {
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
    public ParcelaResponse(@NotBlank String identificador,
                          @NotNull @Positive Integer quantidade,
                          @NotNull @Positive BigDecimal valor) {
        this.identificador = identificador;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public ParcelaWrapper converte() {
        return new ParcelaWrapper(this.identificador, this.quantidade, this.valor);
    }
}
