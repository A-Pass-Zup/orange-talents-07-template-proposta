package br.com.zupacademy.apass.microservicepropostas.cartao;

import br.com.zupacademy.apass.microservicepropostas.proposta.Proposta;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String identificador;

    @NotNull
    private LocalDateTime emitidoEm;

    @NotBlank
    @NotNull
    private String titular;

    @NotNull
    @Positive
    private Integer limite;

    @OneToOne(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private Renegociacao renegociacao;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private List<Bloqueio> bloqueios = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private List<AvisoViagem> avisosViagens = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private List<CarteiraDigital> carteirasDigitais = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private List<Parcela> parcelas = new ArrayList<>();

    @OneToOne(optional = false)
    private Proposta proposta;

    /**
     * Construtor padrão para JPA. Não utilize.
     */
    @Deprecated
    protected Cartao(){
    }

    /**
     *
     * @param identificador Número do cartão
     * @param emitidoEm
     * @param titular
     * @param limite
     * @param renegociacao
     * @param bloqueios
     * @param avisosViagens
     * @param carteirasDigitais
     * @param parcelas
     * @param proposta
     */
    public Cartao(@NotBlank String identificador,
                  @NotNull LocalDateTime emitidoEm,
                  @NotBlank String titular,
                  @NotNull @Positive Integer limite,
                  Optional<RenegociacaoWrapper> renegociacao,
                  @NotNull List<BloqueioWrapper> bloqueios,
                  @NotNull List<AvisoViagemWrapper> avisosViagens,
                  @NotNull List<CarteiraDigitalWrapper> carteirasDigitais,
                  @NotNull List<ParcelaWrapper> parcelas,
                  @NotNull Proposta proposta) {

        Assert.hasText(identificador, "Não pode criar cartão com identificar nulo ou vazio!");
        Assert.notNull(emitidoEm, "Não pode criar cartão sem data/hora de emissão!");
        Assert.hasText(titular, "Não pode criar cartão com titular nulo ou vazio!");
        Assert.notNull(limite, "Não pode criar cartão com limite nulo!");
        Assert.isTrue(limite > 0, "Limite do cartão precisa ser > 0!");
        Assert.notNull(bloqueios, "Não pode criar cartão sem lista de bloqueios!");

        Assert.notNull(bloqueios, "Não pode criar cartão com lista de bloqueios nula!");
        Assert.notNull(avisosViagens, "Não pode criar cartão com lista de avisos de viagens nula!");
        Assert.notNull(carteirasDigitais, "Não pode criar cartão com lista de carteiras digitais nula!");
        Assert.notNull(parcelas, "Não pode criar cartão com lista de parcelas nula!");

        Assert.notNull(proposta, "Não pode criar o cartão sem a proposta!");

        this.identificador = identificador;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;

        renegociacao.ifPresent(value -> this.renegociacao = value.converte(this));

        this.bloqueios = bloqueios.stream().map(b -> b.converte(this)).collect(Collectors.toList());
        this.avisosViagens = avisosViagens.stream().map(av -> av.converte(this)).collect(Collectors.toList());
        this.carteirasDigitais = carteirasDigitais.stream().map(cd -> cd.converte(this)).collect(Collectors.toList());
        this.parcelas = parcelas.stream().map(p -> p.converte(this)).collect(Collectors.toList());

        this.proposta = proposta;
    }
}
