package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.aviso_viagem.AvisoViagemResponse;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.bloqueio.BloqueioResponse;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.carteira_digital.CarteiraDigitalResponse;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.parcela.ParcelaResponse;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.renegociacao.RenegociacaoResponse;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.vencimento.VencimentoResponse;
import br.com.zupacademy.apass.microservicepropostas.proposta.PropostaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private Optional<RenegociacaoResponse> renegociacao;

    @NotNull
    private VencimentoResponse vencimento;

    @NotNull
    private String idProposta;

    /**
     *
     * @param id Número do cartão
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
    public CartaoResponse(@NotEmpty String id,
                          @NotNull LocalDateTime emitidoEm,
                          @NotBlank String titular,
                          @NotNull List<BloqueioResponse> bloqueios,
                          @NotNull List<AvisoViagemResponse> avisos,
                          @NotNull List<CarteiraDigitalResponse> carteiras,
                          @NotNull List<ParcelaResponse> parcelas,
                          @NotNull @Positive Integer limite,
                          Optional<RenegociacaoResponse> renegociacao,
                          @NotNull VencimentoResponse vencimento,
                          @NotBlank String idProposta) {
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
        this.idProposta = idProposta;
    }

    public Cartao converte(PropostaRepository propostaRepository) {

        final var proposta = propostaRepository
                .findByIdentificador(this.idProposta)
                .orElseThrow( () -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha com id de proposta!"));

        return new Cartao(this.id,
                this.emitidoEm,
                this.titular,
                this.limite,
                this.renegociacao.map(RenegociacaoResponse::converte),
                this.bloqueios.stream().map(BloqueioResponse::converte).collect(Collectors.toList()),
                this.avisos.stream().map(AvisoViagemResponse::converte).collect(Collectors.toList()),
                this.carteiras.stream().map(CarteiraDigitalResponse::converte).collect(Collectors.toList()),
                this.parcelas.stream().map(ParcelaResponse::converte).collect(Collectors.toList()),
                this.vencimento.converte(),
                proposta);

    }
}
