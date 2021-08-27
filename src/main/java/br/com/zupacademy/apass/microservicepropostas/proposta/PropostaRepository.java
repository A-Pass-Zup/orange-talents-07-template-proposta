package br.com.zupacademy.apass.microservicepropostas.proposta;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, String> {
    Boolean existsByDocumento(String documento);

    List<Proposta> findByStatusAndCartaoIsNull(StatusProposta status);

    @Transactional
    Proposta save(Proposta proposta);
}
