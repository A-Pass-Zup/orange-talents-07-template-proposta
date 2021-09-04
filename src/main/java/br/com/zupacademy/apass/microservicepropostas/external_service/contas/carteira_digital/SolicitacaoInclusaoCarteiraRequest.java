package br.com.zupacademy.apass.microservicepropostas.external_service.contas.carteira_digital;

import br.com.zupacademy.apass.microservicepropostas.cartao.carteira_digital.CarteiraDigital;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitacaoInclusaoCarteiraRequest {

    @NotNull
    private CarteiraDigital carteiraDigital;

    public SolicitacaoInclusaoCarteiraRequest(@NotNull CarteiraDigital carteiraDigital) {
        this.carteiraDigital = carteiraDigital;
    }

    @NotBlank
    public String getEmail() {
        return this.carteiraDigital.getEmail();
    }

    @NotBlank
    public String getCarteira() {
        return this.carteiraDigital.getEmissor();
    }
}
