package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "contas-cartoes", url = "${servico-externo.contas.url}")
public interface ContasECartoesClient {

    @GetMapping("${servico-externo.contas.cartoes.urn}")
    CartaoResponse getCartoes(String propostaId);
}
