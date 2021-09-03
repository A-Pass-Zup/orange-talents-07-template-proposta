package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import br.com.zupacademy.apass.microservicepropostas.cartao.aviso_viagem.AvisoViagem;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class SolicitacaoAvisoViagemRequest {

    private AvisoViagem avisoViagem;

    public SolicitacaoAvisoViagemRequest(@NotNull AvisoViagem avisoViagem) {
        Assert.notNull(avisoViagem, "Aviso de viagem não pode ser nulo!");

        this.avisoViagem = avisoViagem;
    }

    @NotBlank
    public String getDestino() {
        return this.avisoViagem.getDestino();
    }

    @NotNull
    public LocalDate validoAte() {
        return this.avisoViagem.getValidoAte();
    }
}
