package br.com.zupacademy.apass.microservicepropostas.cartao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class CarteiraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Cartao cartao;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String identificador;

    @NotBlank
    @Email
    private String email;

    /**
     * Construtor padrão para JPA. Não utilize.
     */
    @Deprecated
    protected CarteiraDigital() {}

    /**
     * Construtor com dados obrigatórios.
     *
     * @param cartao
     * @param identificador
     * @param email
     */
    public CarteiraDigital(@NotNull Cartao cartao,
                           @NotBlank String identificador,
                           @NotBlank @Email String email) {
        this.identificador = identificador;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarteiraDigital that = (CarteiraDigital) o;
        return cartao.equals(that.cartao) && identificador.equals(that.identificador) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartao, identificador, email);
    }
}
