package br.com.zupacademy.apass.microservicepropostas.cartao.bloqueio;

import br.com.zupacademy.apass.microservicepropostas.cartao.CartaoRepository;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.ContasECartoesClient;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.bloqueio.SolicitacaoBloqueioRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificaBloqueio implements Runnable{

    @Autowired
    private ContasECartoesClient contasECartoesClient;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Scheduled(fixedDelay = 5000)
    @Override
    public void run() {
        final var cartoes = this.cartaoRepository.cartesComStatusNaoBloqueadoBloqueioAtivo();


        for(var cartao: cartoes) {
            cartao.findBloqueioAtivo().ifPresent(bloqueio -> {
                try {
                    contasECartoesClient.notificaBloqueio(
                            new SolicitacaoBloqueioRequest(bloqueio),
                            cartao.getNumero());

                    cartao.alterarStatusParaBloqueado();

                    this.cartaoRepository.save(cartao);
                } catch (FeignException feignException) {
                    feignException.printStackTrace();
                }
            });
        }
    }
}
