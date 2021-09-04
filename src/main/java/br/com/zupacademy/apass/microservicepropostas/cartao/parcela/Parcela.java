package br.com.zupacademy.apass.microservicepropostas.cartao.parcela;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Parcela
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Cartao cartao;

    @Column(nullable = false, unique = true)
    private String identificador;

    @NotNull
    private Integer quantidade;

    @NotNull
    private BigDecimal valor;


    /**
     * Construtor padrão para JPA. Não utilize.
     */
    @Deprecated
    protected Parcela(){}

    /**
     * Construtor com dados obrigatórios.
     *
     * @param cartao
     * @param identificador
     * @param quantidade
     * @param valor
     */
    public Parcela(@NotNull Cartao cartao,
                   @NotBlank String identificador,
                   @NotNull @Positive Integer quantidade,
                   @NotNull @Positive BigDecimal valor) {

        Assert.notNull(cartao, "Não pode criar parcela sem definir o cartão!");
        Assert.hasText(identificador, "Não pode criar parcela sem identificador!");
        Assert.notNull(quantidade, "A quantidade de parcelas não pode ser nulo!");
        Assert.isTrue(quantidade > 0, "A quantidade de parcelas precisa ser > 0!");
        Assert.notNull(valor, "O valor da parcela não pode ser nulo!");
        Assert.isTrue(valor.compareTo(BigDecimal.ZERO) > 0, "O valor da parcela precisa ser > 0!");

        this.cartao = cartao;
        this.identificador = identificador;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcela parcela = (Parcela) o;
        return cartao.equals(parcela.cartao) && identificador.equals(parcela.identificador) && quantidade.equals(parcela.quantidade) && valor.equals(parcela.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartao, identificador, quantidade, valor);
    }
}
