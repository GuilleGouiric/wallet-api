package com.wallet.service.validation;

import com.wallet.enums.ApiError;
import com.wallet.exception.ApiErrorException;
import com.wallet.port.out.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class WalletValidator {

    private final WalletRepository walletRepository;

    public void ownerAlreadyHasWallet(Long ownerId){
        walletRepository.findByOwnerId(ownerId).
                ifPresent( w -> {
                    throw new ApiErrorException(ApiError.OWNER_ALREADY_HAS_WALLET,String.valueOf(ownerId));
                });
    }
    
    public void validateHistoricalDate(LocalDateTime walletDate, LocalDateTime historicalDate){
        if(historicalDate.isBefore(walletDate)){
            throw new ApiErrorException(ApiError.INVALID_HISTORICAL_DATE);
        }
    }

}
