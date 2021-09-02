package br.com.zupacademy.apass.microservicepropostas.cartao.bloqueio;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Cartao cartao;

    @Column(nullable = false, unique = true)
    private String identificador;

    @NotNull
    private LocalDateTime bloqueadoEm;

    @NotNull
    private String sistemaResponsavel;

    @NotNull
    private Boolean ativo;

    private String userAgent;

    private String ip;

    /**
     * Construtor para JPA. Não utilize.
     */
    @Deprecated
    protected Bloqueio(){
    }

    /**
     * Construtor com dados obrigatórios.
     *
     * @param identificador
     * @param bloqueadoEm
     * @param sistemaResponsavel
     * @param ativo
     */
    public Bloqueio(@NotNull Cartao cartao,
                    @NotEmpty String identificador,
                    @NotNull LocalDateTime bloqueadoEm,
                    @NotEmpty String sistemaResponsavel,
                    @NotNull Boolean ativo) {

        Assert.notNull(cartao, "Não pode criar bloqueio sem definir o cartão!");
        Assert.hasText(identificador, "Não pode criar bloqueio sem identificador!");
        Assert.notNull(bloqueadoEm, "Não pode criar bloqueio sem data e hora!");
        Assert.hasText(sistemaResponsavel, "Não pode criar bloqueio sem sistema responsável!");
        Assert.notNull(ativo, "Não pode criar bloqueio sem definir se está ativo ou não!");

        this.cartao = cartao;
        this.identificador = identificador;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    /**
     *
     * @param cartao
     * @param identificador
     * @param bloqueadoEm
     * @param sistemaResponsavel
     * @param ativo
     * @param possivelUserAgent
     * @param possivelIp
     */
    public Bloqueio(@NotNull Cartao cartao,
                    @NotEmpty String identificador,
                    @NotNull LocalDateTime bloqueadoEm,
                    @NotEmpty String sistemaResponsavel,
                    @NotNull Boolean ativo,
                    Optional<String> possivelUserAgent,
                    Optional<String> possivelIp) {

        this(cartao, identificador, bloqueadoEm, sistemaResponsavel, ativo);

        System.out.println(this.sistemaResponsavel);

        possivelUserAgent.ifPresent(ua -> this.userAgent = ua);
        possivelIp.ifPresent(ip -> this.ip = ip);
    }

    /**
     *
     * @return
     */
    public Boolean estaAtivo() {
        return ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bloqueio bloqueio = (Bloqueio) o;

        return cartao.equals(bloqueio.cartao) &&
                identificador.equals(bloqueio.identificador) &&
                bloqueadoEm.equals(bloqueio.bloqueadoEm) &&
                sistemaResponsavel.equals(bloqueio.sistemaResponsavel) &&
                ativo.equals(bloqueio.ativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartao, identificador, bloqueadoEm, sistemaResponsavel, ativo);
    }
}
