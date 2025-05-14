package com.wallet.port.in;

import com.wallet.dto.request.CreateWalletRequestDTO;
import com.wallet.dto.request.MovementRequestDTO;
import com.wallet.dto.response.CreateWalletResponseDTO;
import com.wallet.dto.response.HistoricBalanceResponseDTO;

import java.time.LocalDateTime;

public interface WalletUseCase {

    CreateWalletResponseDTO createWallet(CreateWalletRequestDTO input);
    CreateWalletResponseDTO get(Long walletId);
    CreateWalletResponseDTO deposit(MovementRequestDTO input, Long walletId);
    CreateWalletResponseDTO withdraw(MovementRequestDTO input, Long walletId);
    HistoricBalanceResponseDTO getHistoricalBalance(Long walletId, LocalDateTime date);
}
