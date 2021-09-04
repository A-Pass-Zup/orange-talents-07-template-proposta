package br.com.zupacademy.apass.microservicepropostas.cartao.carteira_digital;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

public class CarteiraDigitalWrapper {

    @NotBlank
    private String idExterno;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private LocalDateTime associadaEm;

    @NotBlank
    private String emissor;

    public CarteiraDigitalWrapper(@NotBlank String idExterno,
                                  @NotBlank @Email String email,
                                  @NotNull LocalDateTime associadaEm,
                                  @NotBlank String emissor) {
        this.idExterno = idExterno;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public CarteiraDigital converte(Cartao cartao) {
        return new CarteiraDigital(cartao,
                Optional.of(this.idExterno),
                this.email,
                this.associadaEm,
                this.emissor);
    }
}
