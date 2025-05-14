package com.wallet.enums;

import org.springframework.http.HttpStatus;

public enum ApiError {

    OWNER_ALREADY_HAS_WALLET(HttpStatus.BAD_REQUEST,4001, "Wallet already exists for owner Id: "),
    INVALID_REQUEST_PARAMS(HttpStatus.BAD_REQUEST,4002, "Invalid request params: "),
    INVALID_HISTORICAL_DATE(HttpStatus.BAD_REQUEST,4003, "Historical Date is before wallet creation "),
    WALLET_NOT_FOUND(HttpStatus.NOT_FOUND,4041, "Wallet not found for wallet id: "),
    INSUFFICIENT_FUNDS(HttpStatus.CONFLICT,4091, "Insufficient funds to do the operation "),
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
