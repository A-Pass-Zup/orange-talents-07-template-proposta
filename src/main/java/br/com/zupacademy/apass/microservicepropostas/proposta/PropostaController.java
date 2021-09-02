package br.com.zupacademy.apass.microservicepropostas.proposta;

import br.com.zupacademy.apass.microservicepropostas.external_service.analise.SolicitacaoAnalise;
import br.com.zupacademy.apass.microservicepropostas.external_service.analise.SolicitacaoAnaliseClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/proposta")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private SolicitacaoAnaliseClient solicitacaoAnaliseClient;

    @PostMapping
    public ResponseEntity<?> cadastra(@Valid @RequestBody PropostaRequest propostaRequest, UriComponentsBuilder uriComponentsBuilder) {

        final var novaProposta = propostaRequest.converte();

        if(this.propostaRepository.existsByDocumento(novaProposta.getDocumento())) {
            return ResponseEntity.unprocessableEntity().build();
        }

        try {
            novaProposta.setStatus(
                    this.solicitacaoAnaliseClient
                            .analisa(new SolicitacaoAnalise(novaProposta))
                            .getResultadoSolicitacao()
                            .getStatusProposta()
            );
        } catch (FeignException.UnprocessableEntity unprocessableEntity) {
            unprocessableEntity.printStackTrace();
            novaProposta.setStatus(StatusProposta.NAO_ELEGIVEL);
        } catch (FeignException feignException) {
            feignException.printStackTrace();
            return ResponseEntity.status(feignException.status()).body(feignException.responseBody());
        }

        propostaRepository.save(novaProposta);

        return ResponseEntity.created(
                uriComponentsBuilder
                        .path("/api/v1/proposta/{identificador}").buildAndExpand(novaProposta.getIdentificador())
                        .toUri()
        ).build();
    }

    @GetMapping("{identificador}")
    public ResponseEntity<PropostaResponse> acompanhamento(@PathVariable String identificador) {
        final var possivelProposta= this.propostaRepository.findByIdentificador(identificador);
        if(possivelProposta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new PropostaResponse(possivelProposta.get()));
    }
}
