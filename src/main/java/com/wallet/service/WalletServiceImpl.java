package com.wallet.service;

import com.wallet.context.Constants;
import com.wallet.domain.Wallet;
import com.wallet.dto.request.CreateWalletRequestDTO;
import com.wallet.dto.request.MovementRequestDTO;
import com.wallet.dto.request.TransferRequestDTO;
import com.wallet.dto.response.CreateWalletResponseDTO;
import com.wallet.dto.response.HistoricBalanceResponseDTO;
import com.wallet.dto.response.TransferResponseDTO;
import com.wallet.enums.TransferType;
import com.wallet.mapper.WalletMapper;
import com.wallet.port.in.TransferUseCase;
import com.wallet.port.in.WalletUseCase;
import com.wallet.port.out.WalletRepository;
import com.wallet.service.validation.WalletValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletUseCase {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;
    private final WalletValidator walletValidator;
    private final TransferUseCase transferOrchestrator;

    public CreateWalletResponseDTO createWallet(CreateWalletRequestDTO input){
        Long ownerId = input.getOwnerId();
        walletValidator.ownerAlreadyHasWallet(ownerId);
        Wallet wallet = new Wallet(ownerId, input.getDescription());
        return walletMapper.toResponse(walletRepository.save(wallet));
    }

    public CreateWalletResponseDTO get(Long walletId){
        return walletMapper.toResponse(walletRepository.findById(walletId));
    }

    public CreateWalletResponseDTO deposit(MovementRequestDTO input, Long walletId){

        TransferRequestDTO transferRequest = new TransferRequestDTO(Constants.DEPOSIT_SINK_ID, walletId, input.getAmount());
        TransferResponseDTO transferResponse = transferOrchestrator.transfer(transferRequest, TransferType.DEPOSIT);
        return walletMapper.toResponse(transferResponse.getTarget());

    }
    public CreateWalletResponseDTO withdraw(MovementRequestDTO input, Long walletId){

        TransferRequestDTO transferRequest = new TransferRequestDTO(walletId, Constants.WITHDRAW_SINK_ID,input.getAmount());
        TransferResponseDTO transferResponse = transferOrchestrator.transfer(transferRequest, TransferType.WITHDRAW);
        return walletMapper.toResponse(transferResponse.getSource());
    }
    
    public HistoricBalanceResponseDTO getHistoricalBalance(Long walletId, LocalDateTime date){
        
        Wallet wallet = walletRepository.findById(walletId);
        walletValidator.validateHistoricalDate(wallet.getDateCreated(), date);
        BigDecimal historicalBalance = transferOrchestrator.getHistoricalBalance(walletId,date);

        return HistoricBalanceResponseDTO
                .builder()
                .balance(historicalBalance)
                .walletId(walletId)
                .balanceDate(date)
                .build();
    }
}
