package br.com.zupacademy.apass.microservicepropostas.cartao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findByIdentificador(String identificador);
}
