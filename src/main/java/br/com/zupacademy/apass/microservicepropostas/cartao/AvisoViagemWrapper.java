package br.com.zupacademy.apass.microservicepropostas.cartao;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AvisoViagemWrapper {
    @NotNull
    private LocalDateTime validoAte;

    @NotEmpty
    private String destino;

    public AvisoViagemWrapper(@NotNull LocalDateTime validoAte, @NotEmpty String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public AvisoViagem converte(Cartao cartao) {
        return new AvisoViagem(cartao, this.validoAte, this.destino);
    }
}
