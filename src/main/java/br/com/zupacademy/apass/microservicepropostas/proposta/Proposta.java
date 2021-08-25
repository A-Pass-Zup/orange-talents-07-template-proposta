package br.com.zupacademy.apass.microservicepropostas.proposta;

import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String documento;

    @Email
    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    private String endereco;

    @Positive
    @NotNull
    private BigDecimal salario;

    /**
     * Construtor para JPA. Não use.
     */
    @Deprecated
    protected Proposta() {
    }

    /**
     * Construtor com os dados padrões.
     *
     * @param email
     * @param nome
     * @param endereco
     * @param salario
     */
    public Proposta(@NotBlank String documento,
                    @Email @NotBlank String email,
                    @NotBlank String nome,
                    @NotBlank String endereco,
                    @NotNull @Positive BigDecimal salario) {

        Assert.hasText(documento, "Não pode criar uma proposta sem documento!");

        Assert.hasText(email, "Não pode criar uma proposta sem e-mail!");

        Assert.hasText(nome, "Não pode criar uma proposta sem nome!");

        Assert.hasText(endereco, "Não pode criar proposta sem endereço!");

        Assert.notNull(salario, "Não pode criar uma proposta com salário nulo!");
        Assert.isTrue(salario.compareTo(BigDecimal.ZERO) > 0, "Salário da proposta precisa ser  >0 !");

        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    /**
     * Obtém o código/id da proposta.
     *
     * @return
     */
    public Long getId() {
        return id;
    }
}
