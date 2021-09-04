package br.com.zupacademy.apass.microservicepropostas.cartao.carteira_digital;

import br.com.zupacademy.apass.microservicepropostas.cartao.CartaoRepository;
import br.com.zupacademy.apass.microservicepropostas.cartao.LocalizadorCartao;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.ContasECartoesClient;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.carteira_digital.SolicitacaoInclusaoCarteiraRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/carteira-digital")
public class CarteiraDigitalController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraDigitalRepository carteiraDigitalRepository;

    @Autowired
    private ContasECartoesClient contasECartoesClient;

    @PostMapping("/cartao/{identificadorCartao}")
    public ResponseEntity<?> associaComCartao(@PathVariable String identificadorCartao,
                                              @RequestBody @Valid AssociaCarteiraRequest associaCarteiraRequest,
                                              UriComponentsBuilder uriComponentsBuilder) {

        final var cartao = new LocalizadorCartao(this.cartaoRepository)
                .localizaPorIndentificador(identificadorCartao);

        final var carteira = associaCarteiraRequest.converte(cartao, this.carteiraDigitalRepository);

        try {
            final var resultado = this.contasECartoesClient.associaCarteira(
                    new SolicitacaoInclusaoCarteiraRequest(carteira),
                    cartao.getNumero());

            carteira.setIdExterno(resultado.getId());

            this.carteiraDigitalRepository.save(carteira);

        } catch (FeignException feignException) {
            feignException.printStackTrace();
            return ResponseEntity.status(feignException.status()).body(feignException.getMessage());
        }

        return ResponseEntity.created(
                uriComponentsBuilder.path("/api/v1/carteira-digital/{identificadorCarteira}")
                .buildAndExpand(carteira.getIdentificador())
                .toUri()
        ).build();
    }
}
