package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(value = "contas-cartoes", url = "${servico-externo.contas.base-uri}")
public interface ContasECartoesClient {

    @GetMapping(value = "${servico-externo.contas.cartoes.urn}")
    @Valid CartaoResponse getCartoes(@RequestParam String idProposta);

    @PostMapping(value = "${servico-externo.contas.cartoes.bloqueio.urn}")
    @Valid ResultadoBloqueio notificaBloqueio(@RequestBody @Valid SolicitacaoBloqueioRequest request, @PathVariable("id") String numeroCartao);

    @PostMapping(value = "${servico-externo.contas.cartoes.aviso.urn}")
    @Valid ResultadoAviso notificaAvisoViagem(@RequestBody @Valid SolicitacaoAvisoViagemRequest request, @PathVariable("id") String numeroCartao);
}
