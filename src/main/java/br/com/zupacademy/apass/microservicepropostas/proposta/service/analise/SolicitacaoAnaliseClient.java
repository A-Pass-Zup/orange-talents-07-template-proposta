package br.com.zupacademy.apass.microservicepropostas.proposta.service.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "analise", url = "${service.analise.solicitacao.url}")
public interface SolicitacaoAnaliseClient {

    @RequestMapping(method = RequestMethod.POST)
    ResultadoAnalise analisa(SolicitacaoAnalise solicitacaoAnalise);
}
