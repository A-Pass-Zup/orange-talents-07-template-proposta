package br.com.zupacademy.apass.microservicepropostas.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, String> {
    Boolean existsByDocumento(String documento);

    Optional<Proposta> findByIdentificador(String identificador);

    @Query("FROM Proposta p WHERE p NOT IN (SELECT proposta FROM Cartao) AND p.status = :status")
    List<Proposta> propostasSemCartaoVinculado(StatusProposta status);

}
