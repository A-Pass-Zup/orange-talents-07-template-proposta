package br.com.zupacademy.apass.microservicepropostas.proposta.service.analise;

import br.com.zupacademy.apass.microservicepropostas.proposta.Proposta;
import org.springframework.util.Assert;

public class SolicitacaoAnalise {

    private Proposta proposta;

    public SolicitacaoAnalise(Proposta proposta) {

        Assert.hasText(proposta.getDocumento(), "Dcoumento não pode ser vazio");

        this.proposta = proposta;

    }

    public String getDocumento() {
        return this.proposta.getDocumento();
    }

    public String getNome() {
        return this.proposta.getNome();
    }

    public String getIdProposta() {
        return this.proposta.getIdentificador();
    }
}
