package com.wallet.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class HistoricBalanceResponseDTO {
    
    private Long walletId;
    private BigDecimal balance;
    private LocalDateTime balanceDate;
}
