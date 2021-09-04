package br.com.zupacademy.apass.microservicepropostas.cartao.carteira_digital;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraDigitalRepository extends JpaRepository<CarteiraDigital, Long> {
    Boolean existsByEmissor(String emissor);
}
