package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import br.com.zupacademy.apass.microservicepropostas.external_service.contas.aviso_viagem.ResultadoAvisoViagemResponse;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.aviso_viagem.SolicitacaoAvisoViagemRequest;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.bloqueio.ResultadoBloqueioResponse;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.bloqueio.SolicitacaoBloqueioRequest;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.carteira_digital.ResultadoCarteiraResponse;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.carteira_digital.SolicitacaoInclusaoCarteiraRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(value = "contas-cartoes", url = "${servico-externo.contas.base-uri}")
public interface ContasECartoesClient {

    @GetMapping(value = "${servico-externo.contas.cartoes.urn}")
    @Valid CartaoResponse getCartoes(@RequestParam String idProposta);

    @PostMapping(value = "${servico-externo.contas.cartoes.bloqueio.urn}")
    @Valid ResultadoBloqueioResponse notificaBloqueio(@RequestBody @Valid SolicitacaoBloqueioRequest request, @PathVariable("id") String numeroCartao);

    @PostMapping(value = "${servico-externo.contas.cartoes.aviso-viagem.urn}")
    @Valid ResultadoAvisoViagemResponse notificaAvisoViagem(@RequestBody @Valid SolicitacaoAvisoViagemRequest request, @PathVariable("id") String numeroCartao);

    @PostMapping(value = "${servico-externo.contas.cartoes.carteira-digtal.urn}")
    @Valid ResultadoCarteiraResponse associaCarteira(@RequestBody @Valid SolicitacaoInclusaoCarteiraRequest request, @PathVariable("id") String numeroCartao);
}
