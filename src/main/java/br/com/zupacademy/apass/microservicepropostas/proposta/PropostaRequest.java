package br.com.zupacademy.apass.microservicepropostas.proposta;

import br.com.zupacademy.apass.microservicepropostas.security.Crypt;
import br.com.zupacademy.apass.microservicepropostas.validation.constraints.CpfOrCnpj;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank
    @CpfOrCnpj
    private String documento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @Positive
    private BigDecimal salario;


    /**
     *
     * @param documento
     * @param email
     * @param nome
     * @param endereco
     * @param salario
     */
    @JsonCreator // Necessário para o swagger descrever o json
    public PropostaRequest(@NotBlank String documento,
                           @NotBlank String email,
                           @NotBlank String nome,
                           @NotBlank String endereco,
                           @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    /**
     * Converte os dados para o modelo de domínio.
     *
     * @param crypt
     * @return
     */
    public Proposta converte(Crypt crypt) {
        return new Proposta(new DocumentoCrypt(crypt, this.documento.replaceAll("\\D", "")),
                this.email,
                this.nome,
                this.endereco,
                this.salario);
    }
}
