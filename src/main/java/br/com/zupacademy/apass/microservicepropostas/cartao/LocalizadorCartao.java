package br.com.zupacademy.apass.microservicepropostas.cartao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

public class LocalizadorCartao {

    private CartaoRepository cartaoRepository;

    /**
     *
     * @param cartaoRepository
     */
    public LocalizadorCartao(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    /**
     *
     * @param identificador
     * @throws ResponseStatusException HttpStatus.NOT_FOUND se não encontrar o cartão.
     * @return
     */
    public Cartao localizaPorIndentificador(String identificador) {
        return this.cartaoRepository
                .findByIdentificador(identificador)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi encontrado o cartão com indentificador: " + identificador + "!"
                ));
    }
}
