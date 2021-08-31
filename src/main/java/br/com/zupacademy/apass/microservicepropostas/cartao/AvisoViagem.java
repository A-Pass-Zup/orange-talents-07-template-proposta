package br.com.zupacademy.apass.microservicepropostas.cartao;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Cartao cartao;

    @NotNull
    private LocalDateTime validoAte;

    @NotEmpty
    @NotNull
    private String destino;

    /**
     * Construtor padrão para JPA. Não utilize.
     */
    @Deprecated
    protected AvisoViagem(){}

    /**
     * Construtor com dados obrigatórios.
     *
     * @param cartao
     * @param validoAte
     * @param destino
     */
    public AvisoViagem(@NotNull Cartao cartao,
                       @NotNull LocalDateTime validoAte,
                       @NotEmpty String destino) {

        Assert.notNull(cartao, "Não pode criar aviso sem definir o cartão!");
        Assert.notNull(validoAte, "Não pode criar aviso sem a validade!");
        Assert.hasText(destino, "Não pode criar aviso sem destino!");

        this.cartao = cartao;
        this.validoAte = validoAte;
        this.destino = destino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisoViagem that = (AvisoViagem) o;
        return cartao.equals(that.cartao) && validoAte.equals(that.validoAte) && destino.equals(that.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartao, validoAte, destino);
    }
}
