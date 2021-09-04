package br.com.zupacademy.apass.microservicepropostas.cartao.vencimento;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class VencimentoWrapper {

    @NotBlank
    private String identificador;

    @NotNull
    @Range(min = 1, max = 31)
    private Integer dia;

    @NotNull
    private LocalDateTime dataDeCriacao;

    public VencimentoWrapper(@NotBlank String identificador,
                             @NotNull @Range(min = 1, max = 31) Integer dia,
                             @NotNull LocalDateTime dataDeCriacao) {
        this.identificador = identificador;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Vencimento converte(Cartao cartao) {
        return new Vencimento(cartao, this.identificador, this.dia, this.dataDeCriacao);
    }
}
