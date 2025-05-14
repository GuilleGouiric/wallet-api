package com.wallet.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class CreateWalletResponseDTO {

    private Long id;
    private Long ownerId;
    private BigDecimal balance;
    private String description;
    private LocalDateTime dateCreated;
}
