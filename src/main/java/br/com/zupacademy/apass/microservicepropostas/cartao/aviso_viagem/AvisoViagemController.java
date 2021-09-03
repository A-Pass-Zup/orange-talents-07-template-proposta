package br.com.zupacademy.apass.microservicepropostas.cartao.aviso_viagem;

import br.com.zupacademy.apass.microservicepropostas.cartao.CartaoRepository;
import br.com.zupacademy.apass.microservicepropostas.cartao.LocalizadorCartao;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.ContasECartoesClient;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.SolicitacaoAvisoViagemRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/aviso-viagem")
public class AvisoViagemController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private AvisoViagemRepository avisoViagemRepository;

    @Autowired
    private ContasECartoesClient contasECartoesClient;

    @PostMapping("/cartao/{identificadorCartao}")
    @Transactional
    public ResponseEntity<?> cadastra(@PathVariable String identificadorCartao,
                                      @RequestBody @Valid AvisoViagemRequest avisoViagemRequest,
                                      HttpServletRequest request) {

        // Faz a verificação se o cartão existe
        final var cartao = new LocalizadorCartao(this.cartaoRepository)
                .localizaPorIndentificador(identificadorCartao);

        final var novoAvisoViagem = avisoViagemRequest.converte(cartao, request);

        try {
            this.contasECartoesClient.notificaAvisoViagem(
                    new SolicitacaoAvisoViagemRequest(novoAvisoViagem), cartao.getNumero());

            this.avisoViagemRepository.save(novoAvisoViagem);

            return ResponseEntity.ok().build();

        } catch(FeignException feignException) {
            feignException.printStackTrace();
            return ResponseEntity.status(feignException.status()).body(feignException.getMessage());
        }
    }
}
