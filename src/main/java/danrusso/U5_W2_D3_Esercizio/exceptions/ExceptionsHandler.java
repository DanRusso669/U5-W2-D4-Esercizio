package danrusso.U5_W2_D3_Esercizio.exceptions;


import danrusso.U5_W2_D3_Esercizio.payloads.ErrorPayloadListDTO;
import danrusso.U5_W2_D3_Esercizio.payloads.ErrorsPayload;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayloadListDTO handleValidationError(ValidationException ex) {
        return new ErrorPayloadListDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrorMessages());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleBadRequest(BadRequestException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound(NotFoundException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleServerError(Exception ex) {
        ex.printStackTrace();
        return new ErrorsPayload("There's been a problem. We are working on it.", LocalDateTime.now());
    }
}
