package br.com.zupacademy.apass.microservicepropostas.cartao;

import br.com.zupacademy.apass.microservicepropostas.external_service.contas.CartaoResponse;
import br.com.zupacademy.apass.microservicepropostas.external_service.contas.ContasECartoesClient;
import br.com.zupacademy.apass.microservicepropostas.proposta.PropostaRepository;
import br.com.zupacademy.apass.microservicepropostas.proposta.StatusProposta;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class VinculaCartaoComProposta  implements Runnable {

    @Autowired
    private ContasECartoesClient contasECartoesClient;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Override
    @Scheduled(fixedDelay = 5000)
    public void run() {
        final var propostas = this.propostaRepository.propostasSemCartaoVinculado(StatusProposta.ELEGIVEL);

        for (var proposta: propostas) {
            try {
                final var cartaoResponse = contasECartoesClient.getCartoes(proposta.getIdentificador());

                this.cartaoRepository.save(cartaoResponse.converte(this.propostaRepository));

            } catch(FeignException feignException) {
                feignException.printStackTrace();
            }
        }
    }
}
