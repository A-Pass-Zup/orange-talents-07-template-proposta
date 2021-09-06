package br.com.zupacademy.apass.microservicepropostas.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

@Component
public class Crypt {

    @Value("${crypt.secret}")
    private String secret;

    public String encrypt(String value) {
        return new BCryptPasswordEncoder().encode(value);
    }

}
