package br.com.zupacademy.apass.microservicepropostas.external_service.contas.vencimento;

import br.com.zupacademy.apass.microservicepropostas.cartao.vencimento.VencimentoWrapper;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class VencimentoResponse {

    @NotBlank
    private String id;

    @NotNull
    @Range(min=1, max=31)
    private Integer dia;

    @NotNull
    private LocalDateTime dataDeCriacao;

    /**
     * Construtor com dados obrigatórios.
     *
     * @param id
     * @param dia
     * @param dataDeCriacao
     */
    public VencimentoResponse(@NotBlank String id,
                              @NotNull @Range(min=1, max=31) Integer dia,
                              @NotNull LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public VencimentoWrapper converte() {
        return new VencimentoWrapper(this.id, this.dia, this.dataDeCriacao);
    }
}
