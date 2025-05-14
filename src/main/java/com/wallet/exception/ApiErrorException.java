package com.wallet.exception;

import com.wallet.dto.ErrorDTO;
import com.wallet.enums.ApiError;
import lombok.Data;


@Data
public class ApiErrorException extends RuntimeException{

    private final ErrorDTO error;

    public ApiErrorException(ErrorDTO error){
        super(error.getDescription());
        this.error = error;
    }

    public ApiErrorException(ApiError error){
        this(new ErrorDTO(error));
    }

    public ApiErrorException(ApiError apiError, String param){
        this(new ErrorDTO(apiError,param));
    }

}
