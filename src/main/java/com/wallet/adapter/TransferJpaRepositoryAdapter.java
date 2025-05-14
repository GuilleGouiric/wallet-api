package com.wallet.adapter;

import com.wallet.adapter.repository.sql.TransferJpaRepository;
import com.wallet.adapter.repository.sql.mapper.TransferEntityMapper;
import com.wallet.domain.Transfer;
import com.wallet.port.out.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class TransferJpaRepositoryAdapter implements TransferRepository {

    private final TransferJpaRepository repository;
    private final TransferEntityMapper mapper;

    public void save(Transfer transfer){
        repository.save(mapper.toEntity(transfer));
    }

    public BigDecimal getHistoricalBalance(Long walletId, LocalDateTime date) {
        return repository.getHistoricalBalance(walletId,date);
    }
}
