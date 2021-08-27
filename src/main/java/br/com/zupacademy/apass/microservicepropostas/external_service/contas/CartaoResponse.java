package br.com.zupacademy.apass.microservicepropostas.external_service.contas;

import java.time.LocalDateTime;
import java.util.List;

public class CartaoResponse {

    private String id;

    private LocalDateTime emitidoEm;

    private String titular;

    private List<BloqueioResponse> bloqueios;

    private List<AvisoViagemResponse> avisos;

    private List<CarteiraDigitalResponse> carteiras;


}
