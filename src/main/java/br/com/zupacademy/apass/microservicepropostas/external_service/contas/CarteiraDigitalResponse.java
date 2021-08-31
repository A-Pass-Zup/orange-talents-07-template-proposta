package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;
import br.com.zupacademy.apass.microservicepropostas.cartao.CarteiraDigital;
import br.com.zupacademy.apass.microservicepropostas.cartao.CarteiraDigitalWrapper;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CarteiraDigitalResponse {
    @NotBlank
    private String identificador;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private LocalDateTime associadaEm;

    @NotBlank
    private String emissor;

    public CarteiraDigitalResponse(@NotBlank String identificador,
                                   @NotBlank @Email String email,
                                   @NotNull LocalDateTime associadaEm,
                                   @NotBlank String emissor) {
        this.identificador = identificador;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public CarteiraDigitalWrapper converte() {
        return new CarteiraDigitalWrapper(this.identificador, this.email, this.associadaEm, this.emissor);
    }

}
