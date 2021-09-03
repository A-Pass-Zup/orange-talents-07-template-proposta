package br.com.zupacademy.apass.microservicepropostas.cartao.aviso_viagem;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public class AvisoViagemWrapper {
    @NotNull
    private LocalDate validoAte;

    @NotEmpty
    private String destino;

    private String ip;

    private String userAgent;

    public AvisoViagemWrapper(@NotNull LocalDate validoAte, @NotEmpty String destino,
                              Optional<String> ip,
                              Optional<String> userAgent) {
        this.validoAte = validoAte;
        this.destino = destino;

        ip.ifPresent(ip_ -> this.ip = ip_);
        userAgent.ifPresent(ua -> this.userAgent = ua);
    }

    public AvisoViagem converte(Cartao cartao) {
        return new AvisoViagem(cartao,
                this.validoAte,
                this.destino,
                Optional.of(this.ip),
                Optional.of(this.userAgent));
    }
}
