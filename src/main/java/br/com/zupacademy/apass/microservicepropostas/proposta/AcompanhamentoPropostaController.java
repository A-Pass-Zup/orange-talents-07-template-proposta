package br.com.zupacademy.apass.microservicepropostas.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/proposta")
public class AcompanhamentoPropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @GetMapping("{identificador}")
    public ResponseEntity<PropostaResponse> acompanhamento(@PathVariable String identificador) {
        final var possivelProposta= this.propostaRepository.findByIdentificador(identificador);
        if(possivelProposta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new PropostaResponse(possivelProposta.get()));
    }
}
