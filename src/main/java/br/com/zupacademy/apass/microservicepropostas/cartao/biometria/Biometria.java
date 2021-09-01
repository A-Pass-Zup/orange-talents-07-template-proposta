package br.com.zupacademy.apass.microservicepropostas.cartao.biometria;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private Cartao cartao;

    @Column(nullable = false, unique = true)
    private String identificador = UUID.randomUUID().toString();

    @Column(nullable = false)
    private LocalDateTime associadaEm = LocalDateTime.now();

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String biometria;

    /**
     * Construtor para JPA. Não utilize.
     */
    @Deprecated
    protected Biometria() {
    }

    /**
     *
     * @param cartao
     * @param biometria
     */
    public Biometria(Cartao cartao, String biometria) {
        this.cartao = cartao;
        this.biometria = biometria;
    }

    /**
     *
     * @return
     */
    public String getIdentificador() {
        return this.identificador;
    }
}
