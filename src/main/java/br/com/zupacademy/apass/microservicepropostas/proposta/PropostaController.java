package br.com.zupacademy.apass.microservicepropostas.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<?> cadastra(@Valid @RequestBody PropostaRequest propostaRequest, UriComponentsBuilder uriComponentsBuilder) {

        var novaProposta = propostaRequest.converte();

        if(this.propostaRepository.existsByDocumento(novaProposta.getDocumento())) {
            return ResponseEntity.unprocessableEntity().build();
        }

        propostaRepository.save(novaProposta);

        return ResponseEntity.created(
                uriComponentsBuilder
                        .path("/proposta/{id}").buildAndExpand(novaProposta.getId())
                        .toUri()
        ).build();
    }
}
