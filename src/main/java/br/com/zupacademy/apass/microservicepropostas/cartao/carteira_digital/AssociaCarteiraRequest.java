package br.com.zupacademy.apass.microservicepropostas.cartao.carteira_digital;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssociaCarteiraRequest {

    @Email
    @NotBlank
    private String email;

    @NotNull
    private CarteirasDigitais tipoCarteira;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AssociaCarteiraRequest(@NotBlank String email, @NotNull CarteirasDigitais tipoCarteira) {
        this.email = email;
        this.tipoCarteira = tipoCarteira;
    }

    /**
     *
     * @param cartao
     * @return
     */
    public CarteiraDigital converte(Cartao cartao, CarteiraDigitalRepository carteiraDigitalRepository) {

        if(carteiraDigitalRepository.existsByEmissor(this.tipoCarteira.toString())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "A carteira informada já foi associada!");
        }

        return new CarteiraDigital(cartao, this.tipoCarteira, this.email);
    }
}
