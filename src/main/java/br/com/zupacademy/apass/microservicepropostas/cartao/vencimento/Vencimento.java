package br.com.zupacademy.apass.microservicepropostas.cartao.vencimento;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Vencimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private Cartao cartao;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String identificador;

    @Range(min = 1, max = 31)
    @Column(nullable = false)
    private Integer dia;

    @Column(nullable = false)
    private LocalDateTime dataDeCriacao;

    @Deprecated
    protected Vencimento(){
    }

    /**
     *
     * @param cartao
     * @param identificador
     * @param dia
     * @param dataDeCriacao
     */
    public Vencimento(@NotNull Cartao cartao,
                      @NotBlank String identificador,
                      @NotNull @Range(min = 1, max = 31) Integer dia,
                      @NotNull LocalDateTime dataDeCriacao) {

        Assert.notNull(cartao, "Não pode criar vencimento com cartão nulo!");
        Assert.hasText(identificador, "Não pode criar vencimento com identificador vazio!");
        Assert.notNull(dia, "Não pode criar vencimento sem o dia!");
        Assert.isTrue(1 <= dia && dia <= 31, "O dia de vencimento tem que ser entre 1 e 31!");
        Assert.notNull(dataDeCriacao, "Não pode criar o vencimento sem a data/hora de criação!");

        this.cartao = cartao;
        this.identificador = identificador;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vencimento that = (Vencimento) o;
        return cartao.equals(that.cartao) && identificador.equals(that.identificador) && dia.equals(that.dia) && dataDeCriacao.equals(that.dataDeCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartao, identificador, dia, dataDeCriacao);
    }
}
