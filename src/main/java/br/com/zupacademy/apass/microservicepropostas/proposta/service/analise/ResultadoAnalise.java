package br.com.zupacademy.apass.microservicepropostas.proposta.service.analise;

import br.com.zupacademy.apass.microservicepropostas.validation.constraints.CpfOrCnpj;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ResultadoAnalise {

    @NotBlank
    @CpfOrCnpj
    private String documento;

    private String nome;

    @NotNull
    private StatusAnalise resultadoSolicitacao;

    private String idProposta;

    /**
     * Construtor com os dados obrigatórios.
     *
     * @param documento
     * @param nome
     * @param resultadoSolicitacao
     * @param idProposta
     */
    public ResultadoAnalise(String documento, String nome, StatusAnalise resultadoSolicitacao, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    /**
     * Obtém o status do solicitentate do resultado da análise.
     *
     * @return
     */
    public StatusAnalise getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
