package com.wallet.port.out;

import com.wallet.domain.Transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransferRepository {

    void save(Transfer transfer);
    BigDecimal getHistoricalBalance(Long walletId, LocalDateTime date);
}
