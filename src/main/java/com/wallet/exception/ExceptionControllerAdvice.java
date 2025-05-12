package com.wallet.exception;

import com.wallet.dto.ErrorDTO;
import com.wallet.enums.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationError(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

       return handle(ex , new ErrorDTO(ApiError.INVALID_REQUEST_PARAMS, message));

    }

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<ErrorDTO> handleApiError(ApiErrorException ex){
        return handle(ex, ex.getError());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleApiError(Exception ex){
        return handle(ex, ApiError.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorDTO> handle(Throwable th, ApiError error){
        return handle(th, new ErrorDTO(error));
    }

    private ResponseEntity<ErrorDTO> handle(Throwable th, ErrorDTO error){
        log.error("Error :{}", th.getMessage());
        return new ResponseEntity<>(error, error.getHttpStatus());
    }
}
