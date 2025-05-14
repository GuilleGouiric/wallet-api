package com.wallet.adapter.repository.sql;

import com.wallet.adapter.repository.sql.entity.TransferEntity;
import com.wallet.adapter.repository.sql.query.TransferQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransferJpaRepository extends JpaRepository<TransferEntity, Long> {

    @Query(value = TransferQuery.GET_HISTORICAL_BALANCE_QUERY, nativeQuery = true)
    BigDecimal getHistoricalBalance(Long walletId, LocalDateTime date);
}
