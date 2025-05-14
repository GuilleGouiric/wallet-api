package com.wallet.domain;

import com.wallet.enums.TransferType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Transfer {

    private Long id;
    private Long sourceId;
    private Long targetId;
    private BigDecimal amount;
    private TransferType type;
    private LocalDateTime dateCreated;
}
