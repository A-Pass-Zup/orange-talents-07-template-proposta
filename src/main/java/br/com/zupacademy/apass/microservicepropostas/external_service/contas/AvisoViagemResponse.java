package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import br.com.zupacademy.apass.microservicepropostas.cartao.AvisoViagemWrapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AvisoViagemResponse {
    @NotNull
    private LocalDateTime validoAte;

    @NotBlank
    private String destino;

    public AvisoViagemResponse(@NotNull LocalDateTime validoAte, @NotBlank String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public AvisoViagemWrapper converte() {
        return new AvisoViagemWrapper(this.validoAte, this.destino);
    }
}
