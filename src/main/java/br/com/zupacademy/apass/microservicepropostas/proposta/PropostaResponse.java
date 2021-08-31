package br.com.zupacademy.apass.microservicepropostas.proposta;

public class PropostaResponse {

    private Proposta proposta;

    public PropostaResponse(Proposta proposta) {
        this.proposta = proposta;
    }

    public String getId() {
        return proposta.getIdentificador();
    }

    public String getDocumento() {
        return this.proposta.getDocumento();
    }

    public String getNome() {
        return this.proposta.getNome();
    }

    public String getStatus() {
        return this.proposta.getStatus().toString();
    }
}
