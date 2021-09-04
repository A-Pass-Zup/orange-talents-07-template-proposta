package br.com.zupacademy.apass.microservicepropostas.cartao.carteira_digital;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Entity
public class CarteiraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idExterno;

    @ManyToOne(optional = false)
    private Cartao cartao;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String identificador = UUID.randomUUID().toString();

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotNull
    private LocalDateTime associadaEm;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String emissor;

    /**
     * Construtor padrão para JPA. Não utilize.
     */
    @Deprecated
    protected CarteiraDigital() {}

    /**
     * Construtor com dados obrigatórios.
     *
     * @param cartao
     * @param idExterno
     * @param email
     * @param associadaEm
     * @param emissor
     */
    public CarteiraDigital(@NotNull Cartao cartao,
                           @NotBlank Optional<String> idExterno,
                           @NotBlank @Email String email,
                           @NotNull LocalDateTime associadaEm,
                           @NotBlank String emissor) {

        Assert.notNull(cartao, "Cartão da carteira digital não poder ser nulo!");
        Assert.hasText(identificador, "Identificador da carteira digital não pode ser nulo!");
        Assert.hasText(email, "Carteira digital precisa de um e-mail!");
        Assert.notNull(associadaEm, "Data/hora de associação não pode ser nula!");
        Assert.hasText(emissor, "Emissor da carteira digital não pode ser vazio!");

        this.cartao = cartao;
        idExterno.ifPresent(idE -> this.idExterno = idE);
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public CarteiraDigital(Cartao cartao, CarteirasDigitais carteiraDigital, String email) {
        this(cartao, Optional.empty(), email, LocalDateTime.now(), carteiraDigital.toString());
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
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

    public String getEmail() {
        return this.email;
    }

    public String getEmissor() {
        return this.emissor;
    }

    public String getIdentificador() {
        return this.identificador;
    }
}
