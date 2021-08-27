package br.com.zupacademy.apass.microservicepropostas.external_service.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "analise", url = "${servico-externo.analise.solicitacao.uri}")
public interface SolicitacaoAnaliseClient {

    @RequestMapping(method = RequestMethod.POST)
    ResultadoAnalise analisa(SolicitacaoAnalise solicitacaoAnalise);
}
