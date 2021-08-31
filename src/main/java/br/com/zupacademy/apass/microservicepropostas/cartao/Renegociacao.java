package br.com.zupacademy.apass.microservicepropostas.cartao;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Renegociacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private Cartao cartao;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String identificador;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    private LocalDateTime dataDeCriacao = LocalDateTime.now();

    /**
     * Construtor para JPA. Não utilize.
     */
    @Deprecated
    protected Renegociacao(){}

    /**
     * Construtor com dados obrigatórios.
     *
     * @param identificador
     * @param quantidade
     * @param valor
     */
    public Renegociacao(@NotNull Cartao cartao,
                        @NotBlank String identificador,
                        @NotNull @Positive Integer quantidade,
                        @NotNull @Positive BigDecimal valor) {

        Assert.notNull(cartao, "Cartão de renegociação não pode ser nulo!");

        Assert.hasText(identificador, "Identificador de renegociação não pode ser nulo!");

        Assert.notNull(quantidade, "Quantidade de renegociação não pode ser nula!");
        Assert.isTrue(quantidade > 0, "Quantidade de renegociação precisa ser > 0!");

        Assert.notNull(valor, "Valor de renegociação não pode ser nulo!");
        Assert.isTrue(valor.compareTo(BigDecimal.ZERO) > 0, "Valor de renegociação precisa ser > 0!");

        this.identificador = identificador;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Renegociacao that = (Renegociacao) o;
        return identificador.equals(that.identificador) && quantidade.equals(that.quantidade) && valor.equals(that.valor) && dataDeCriacao.equals(that.dataDeCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador, quantidade, valor, dataDeCriacao);
    }
}
