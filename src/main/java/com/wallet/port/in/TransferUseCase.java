package com.wallet.port.in;

import com.wallet.dto.request.TransferRequestDTO;
import com.wallet.dto.response.TransferResponseDTO;
import com.wallet.enums.TransferType;

import java.math.BigDecimal;

import java.time.LocalDateTime;

public interface TransferUseCase {

    TransferResponseDTO transfer(TransferRequestDTO input, TransferType type);
    BigDecimal getHistoricalBalance(Long walletId, LocalDateTime date);
}
