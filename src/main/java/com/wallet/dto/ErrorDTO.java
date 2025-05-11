package com.wallet.dto;

import com.wallet.enums.ApiError;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDTO {

    private int errorCode;
    private String description;
    private HttpStatus httpStatus;

    public ErrorDTO(ApiError apiError){
        this.errorCode = apiError.code;
        this.httpStatus = apiError.status;
        this.description = apiError.message;
    }

    public ErrorDTO(ApiError apiError, String param){
        this.errorCode = apiError.code;
        this.httpStatus = apiError.status;
        this.description = apiError.message + param;
    }
}
