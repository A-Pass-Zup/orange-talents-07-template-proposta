package br.com.zupacademy.apass.microservicepropostas.proposta;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface PropostaRepository extends JpaRepository<Proposta, String> {
    Boolean existsByDocumento(String documento);

    @Transactional
    Proposta save(Proposta proposta);
}
