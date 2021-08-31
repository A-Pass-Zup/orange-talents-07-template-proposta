package br.com.zupacademy.apass.microservicepropostas.cartao;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CarteiraDigitalWrapper {

    @NotBlank
    private String identificador;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private LocalDateTime associadaEm;

    @NotBlank
    private String emissor;

    public CarteiraDigitalWrapper(@NotBlank String identificador,
                                  @NotBlank @Email String email,
                                  @NotNull LocalDateTime associadaEm,
                                  @NotBlank String emissor) {
        this.identificador = identificador;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public CarteiraDigital converte(Cartao cartao) {
        return new CarteiraDigital(cartao, this.identificador, this.email, this.associadaEm, this.emissor);
    }
}
