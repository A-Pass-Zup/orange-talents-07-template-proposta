package br.com.zupacademy.apass.microservicepropostas.cartao.biometria;

import br.com.zupacademy.apass.microservicepropostas.cartao.CartaoRepository;
import br.com.zupacademy.apass.microservicepropostas.validation.ErroValidacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1/biometria/")
public class BiometriaController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository ;

    @PostMapping("/cartao/{identificadorCartao}")
    public ResponseEntity<?> cadastra(@PathVariable @NotBlank String identificadorCartao,
                                      @RequestBody String biometriaBase64,
                                      UriComponentsBuilder uriComponentsBuilder) {

        // Verifica se o cartão existe
        final var possivelCartao = this.cartaoRepository.findByIdentificador(identificadorCartao);
        if (possivelCartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado o cartão com indentificador: " + identificadorCartao + "!");
        }
        final var cartao = possivelCartao.get();

        // Verifica se foi cadastrada uma biometrica para o cartão
        if(cartao.existeBiometria()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão já possui biometria cadastrada!");
        }

        Biometria biometria;
        try {
            biometria = new Biometria(
                    possivelCartao.get(),
                    Arrays.toString(Base64.getDecoder().decode(biometriaBase64)));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new ErroValidacaoResponse("biometria", biometriaBase64, "Base64 inválida!"));
        }

        this.biometriaRepository.save(biometria);

        return ResponseEntity.created(
                uriComponentsBuilder
                        .path("/api/v1/biometria/{identificadorBiometria}")
                        .buildAndExpand(biometria.getIdentificador())
                        .toUri()
        ).build();
    }

}
