package br.com.zupacademy.apass.microservicepropostas.cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findByIdentificador(String identificador);

    @Query("FROM Cartao c WHERE c.statusBloqueio IS NULL AND EXISTS (FROM Bloqueio b WHERE b.cartao = c AND b.ativo = TRUE)")
    List<Cartao> cartesComStatusNaoBloqueadoBloqueioAtivo();
}
