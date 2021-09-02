package br.com.zupacademy.apass.microservicepropostas.cartao.bloqueio;

import br.com.zupacademy.apass.microservicepropostas.cartao.CartaoRepository;
import br.com.zupacademy.apass.microservicepropostas.cartao.LocalizadorCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/v1/bloqueio")
public class BloqueioController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/cartao/{identificadorCartao}")
    public ResponseEntity<?> bloqueia(@PathVariable @NotBlank String identificadorCartao, HttpServletRequest request) {

        // Tenta localizar o cartão
        final var cartao = new LocalizadorCartao(this.cartaoRepository)
                .localizaPorIndentificador(identificadorCartao);

        if(cartao.estaBloqueado()) {
            return ResponseEntity.unprocessableEntity().body("Cartão já está bloqueado!");
        }

        cartao.addBloqueio(new BloqueioWrapper(
                "microserviceproposta",
                request.getHeader("User-Agent"),
                request.getRemoteAddr()
        ));

        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();
    }
}
