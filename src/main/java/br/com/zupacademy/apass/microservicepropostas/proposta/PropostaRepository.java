package br.com.zupacademy.apass.microservicepropostas.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, String> {
    Boolean existsByDocumento(String documento);

    @Query("FROM Proposta p WHERE p NOT IN (SELECT proposta FROM Cartao)")
    List<Proposta> propostasSemCartaoVinculado(StatusProposta status);

}
