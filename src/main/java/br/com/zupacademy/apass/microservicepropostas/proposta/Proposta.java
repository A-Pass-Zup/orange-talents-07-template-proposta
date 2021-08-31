package br.com.zupacademy.apass.microservicepropostas.proposta;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código de identificação da proposta gerado pelo sistema
     */
    @Column(nullable = false, unique = true)
    private String identificador = UUID.randomUUID().toString();

    @NotBlank
    @Pattern(regexp = "^\\d{11}|\\d{14}$")
    @Column(nullable = false, unique = true, length = 14)
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProposta status;

    /**
     * Construtor para JPA. Não use.
     */
    @Deprecated
    protected Proposta() {
    }

    /**
     * Construtor com os dados padrões.
     *
     * @param documento O cpf/cnpj (somente números).
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

        Assert.isTrue(documento.matches("^\\d{11}|\\d{14}$"), "O documento  (cpf/cpj) tem que ser somente números!");

        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    /**
     * Obtém o código identificador da proposta.
     *
     * @return
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Obtém o documento (cpf/cnpj) da proposta (Somente números).
     *
     * @return
     */
    public String getDocumento() {
        return this.documento;
    }

    /**
     * Obtém o nome do solicitante.
     *
     * @return
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Define o status da proposta.
     *
     * @param status
     */
    public void setStatus(StatusProposta status) {
        this.status = status;
    }
}
