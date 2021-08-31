package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

public class CartaoResponse {

    @NotBlank
    private String id;

    @NotNull
    private LocalDateTime emitidoEm;

    @NotBlank
    private String titular;

    @NotNull
    private List<BloqueioResponse> bloqueios;

    @NotNull
    private List<AvisoViagemResponse> avisos;

    @NotNull
    private List<CarteiraDigitalResponse> carteiras;

    @NotNull
    private List<ParcelaResponse> parcelas;

    @NotNull
    @Positive
    private Integer limite;

    private RenegociacaoResponse renegociacao;

    @NotNull
    private VencimentoResponse vencimento;

    /**
     *
     * @param id
     * @param emitidoEm
     * @param titular
     * @param bloqueios
     * @param avisos
     * @param carteiras
     * @param parcelas
     * @param limite
     * @param renegociacao
     * @param vencimento
     */
    public CartaoResponse(String id,
                          LocalDateTime emitidoEm,
                          String titular,
                          List<BloqueioResponse> bloqueios,
                          List<AvisoViagemResponse> avisos,
                          List<CarteiraDigitalResponse> carteiras,
                          List<ParcelaResponse> parcelas,
                          Integer limite,
                          RenegociacaoResponse renegociacao,
                          VencimentoResponse vencimento) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
    }
}
