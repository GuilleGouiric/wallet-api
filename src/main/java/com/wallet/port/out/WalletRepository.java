package com.wallet.port.out;

import com.wallet.domain.Wallet;

import java.util.Optional;

public interface WalletRepository {

    Optional<Wallet> findByOwnerId(Long ownerId);
    Wallet save(Wallet wallet);
}
