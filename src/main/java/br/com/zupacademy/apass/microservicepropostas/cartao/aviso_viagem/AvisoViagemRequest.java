package br.com.zupacademy.apass.microservicepropostas.cartao.aviso_viagem;

import br.com.zupacademy.apass.microservicepropostas.cartao.Cartao;
import br.com.zupacademy.apass.microservicepropostas.validation.constraints.IsFutureLocalDate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class AvisoViagemRequest {

    @NotBlank
    private String destino;

    @NotNull
    @IsFutureLocalDate
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTermino;

    /**
     *
     * @param destino
     * @param dataTermino
     */
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoViagemRequest(@NotBlank String destino, @NotNull @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataTermino) {
        this.destino = destino;
        this.dataTermino = dataTermino;
    }

    /**
     *
     * @param cartao
     * @param request
     * @return
     */
    public AvisoViagem converte(Cartao cartao, HttpServletRequest request) {
        return new AvisoViagem(cartao,
                this.dataTermino,
                this.destino,
                Optional.of(request.getRemoteAddr()),
                Optional.of(request.getHeader("User-Agent")));
    }
}
