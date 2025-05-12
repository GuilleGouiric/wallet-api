package com.wallet.service;

import com.wallet.domain.Wallet;
import com.wallet.dto.request.CreateWalletRequestDTO;
import com.wallet.dto.response.CreateWalletResponseDTO;
import com.wallet.enums.ApiError;
import com.wallet.exception.ApiErrorException;
import com.wallet.mapper.WalletMapper;
import com.wallet.port.in.WalletUseCase;
import com.wallet.port.out.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletUseCase {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    public CreateWalletResponseDTO createWallet(CreateWalletRequestDTO input){
        Long ownerId = input.getOwnerId();
        if(ownerAlreadyHasWallet(ownerId)){
            throw new ApiErrorException(ApiError.OWNER_ALREADY_HAS_WALLET,String.valueOf(ownerId) );
        }

        Wallet wallet = new Wallet(ownerId);
        return walletMapper.toResponse(walletRepository.save(wallet));
    }

    private boolean ownerAlreadyHasWallet(Long ownerId){
        return walletRepository.findByOwnerId(ownerId).isPresent();
    }
}
