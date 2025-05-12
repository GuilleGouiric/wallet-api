package com.wallet.enums;

import org.springframework.http.HttpStatus;

public enum ApiError {

    OWNER_ALREADY_HAS_WALLET(HttpStatus.BAD_REQUEST,4001, "Wallet already exists for owner Id: "),
    INVALID_REQUEST_PARAMS(HttpStatus.BAD_REQUEST,4002, "Invalid request params: "),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,5000, "Internal server error");

    public final HttpStatus status;
    public final int code;
    public final String message;

    ApiError(HttpStatus status, int code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
