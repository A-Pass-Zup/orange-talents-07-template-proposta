package br.com.zupacademy.apass.microservicepropostas.validation;

import br.com.zupacademy.apass.microservicepropostas.validation.ErroValidacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    /**
     *
     * @param validationBindException
     * @return
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErroValidacaoResponse> validationBindException(BindException validationBindException) {
        return this.monstaResposta(validationBindException.getFieldErrors());
    }

    /**
     *
     * @param methodArgumentNotFoundException
     * @return
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErroValidacaoResponse> methodArgumentNotFoundException(MethodArgumentNotValidException methodArgumentNotFoundException) {
        return this.monstaResposta(methodArgumentNotFoundException.getBindingResult().getFieldErrors());
    }

    /**
     *
     * @param fieldsErrors
     * @return
     */
    private List<ErroValidacaoResponse> monstaResposta(List<FieldError> fieldsErrors) {
        List<ErroValidacaoResponse> erros = new ArrayList<>();
        fieldsErrors.forEach( e -> {
            erros.add(new ErroValidacaoResponse(e.getField(), String.valueOf(e.getRejectedValue()), e.getDefaultMessage()));
        });
        return erros;
    }
}
