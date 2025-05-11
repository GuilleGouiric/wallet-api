package com.wallet.port.in;

import com.wallet.dto.request.CreateWalletRequestDTO;
import com.wallet.dto.response.CreateWalletResponseDTO;

public interface WalletUseCase {

    CreateWalletResponseDTO createWallet(CreateWalletRequestDTO input);
}
