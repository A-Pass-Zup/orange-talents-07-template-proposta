package br.com.zupacademy.apass.microservicepropostas.proposta;

import br.com.zupacademy.apass.microservicepropostas.security.Crypt;
import org.springframework.util.Assert;

public class DocumentoCrypt {

    final private  Crypt crypt;

    final private String documento;

    public DocumentoCrypt(Crypt crypt, String documento) {

        Assert.notNull(crypt, "O gerador de encriptador não pode ser nulo!");
        Assert.hasText(documento, "Não pode criar uma proposta sem documento!");
        Assert.isTrue(documento.matches("^\\d{11}|\\d{14}$"), "O documento  (cpf/cpj) tem que ser somente números!");

        this.crypt = crypt;
        this.documento = documento;
    }

    public String getDocumentoCriptografado() {
        return this.crypt.encrypt(this.documento);
    }
}
