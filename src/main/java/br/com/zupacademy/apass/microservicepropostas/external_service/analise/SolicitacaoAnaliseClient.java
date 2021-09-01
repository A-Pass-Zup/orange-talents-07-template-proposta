package br.com.zupacademy.apass.microservicepropostas.external_service.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(value = "analise", url = "${servico-externo.analise.solicitacao.uri}")
public interface SolicitacaoAnaliseClient {

    @RequestMapping(method = RequestMethod.POST)
    @Valid ResultadoAnalise analisa(@Valid SolicitacaoAnalise solicitacaoAnalise);
}
