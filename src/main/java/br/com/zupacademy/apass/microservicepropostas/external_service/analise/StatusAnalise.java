package br.com.zupacademy.apass.microservicepropostas.external_service.analise;

import br.com.zupacademy.apass.microservicepropostas.proposta.StatusProposta;

public enum StatusAnalise {
    COM_RESTRICAO(StatusProposta.NAO_ELEGIVEL),
    SEM_RESTRICAO(StatusProposta.ELEGIVEL);

    private final StatusProposta statusProposta;

    StatusAnalise(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public StatusProposta getStatusProposta() {
        return this.statusProposta;
    }
}
