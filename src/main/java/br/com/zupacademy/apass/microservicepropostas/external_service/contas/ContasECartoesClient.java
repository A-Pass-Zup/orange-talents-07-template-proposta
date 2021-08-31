package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "contas-cartoes", url = "${servico-externo.contas.url}")
public interface ContasECartoesClient {

    @GetMapping(value = "${servico-externo.contas.cartoes.urn}")
    CartaoResponse getCartoes(@RequestParam String idProposta);
}
