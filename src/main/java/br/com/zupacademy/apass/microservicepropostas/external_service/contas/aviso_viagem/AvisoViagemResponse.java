package br.com.zupacademy.apass.microservicepropostas.external_service.contas.aviso_viagem;

import br.com.zupacademy.apass.microservicepropostas.cartao.aviso_viagem.AvisoViagemWrapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public class AvisoViagemResponse {
    @NotNull
    private LocalDate validoAte;

    @NotBlank
    private String destino;

    public AvisoViagemResponse(@NotNull LocalDate validoAte, @NotBlank String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public AvisoViagemWrapper converte() {
        return new AvisoViagemWrapper(this.validoAte, this.destino, Optional.empty(), Optional.empty());
    }
}
