package com.wallet.service;

import com.wallet.domain.Transfer;
import com.wallet.domain.Wallet;
import com.wallet.dto.request.TransferRequestDTO;
import com.wallet.dto.response.TransferResponseDTO;
import com.wallet.enums.TransferType;
import com.wallet.port.in.TransferUseCase;
import com.wallet.port.out.TransferRepository;
import com.wallet.port.out.WalletRepository;
import com.wallet.service.validation.TransferValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferOrchestrator implements TransferUseCase {

    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;
    private final TransferValidator transferValidator;

    @Transactional
    public TransferResponseDTO transfer(TransferRequestDTO input, TransferType type){

        Wallet source = walletRepository.findById(input.getSourceId());
        Wallet target = walletRepository.findById(input.getTargetId());
        transferValidator.validateSourceBalance(source.getBalance(), input.getAmount());
        createTransfer(source.getId(), target.getId(), input.getAmount(), type);
        target.addBalance(input.getAmount());
        source.subtractBalance(input.getAmount());
        walletRepository.save(source);
        walletRepository.save(target);
        return new TransferResponseDTO(source,target);
    }

    private void createTransfer(Long sourceId, Long targetId, BigDecimal amount, TransferType type) {

        Transfer transfer = Transfer
                .builder()
                .sourceId(sourceId)
                .targetId(targetId)
                .amount(amount)
                .type(type)
                .build();

        transferRepository.save(transfer);

    }

    public BigDecimal getHistoricalBalance(Long walletId, LocalDateTime date){
        return transferRepository.getHistoricalBalance(walletId,date);
    }
}
