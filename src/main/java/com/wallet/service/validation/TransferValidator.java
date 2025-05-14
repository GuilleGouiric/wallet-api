package com.wallet.service.validation;

import com.wallet.enums.ApiError;
import com.wallet.exception.ApiErrorException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransferValidator {

    public void validateSourceBalance(BigDecimal sourceBalance, BigDecimal transferAmount){
        if(transferAmount.compareTo(sourceBalance) > 0){
            throw new ApiErrorException(ApiError.INSUFFICIENT_FUNDS);
        }
    }
}
