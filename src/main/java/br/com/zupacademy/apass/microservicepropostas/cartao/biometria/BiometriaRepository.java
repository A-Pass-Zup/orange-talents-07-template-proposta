package br.com.zupacademy.apass.microservicepropostas.cartao.biometria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BiometriaRepository extends JpaRepository<Biometria, Long> {
    Optional<Biometria> findByIdentificador(String identificador);
}
