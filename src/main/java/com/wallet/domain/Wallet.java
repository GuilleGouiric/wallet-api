package com.wallet.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Wallet {

    private Long id;
    private Long ownerId;
    private BigDecimal balance;
    private LocalDate dateCreated;

    public Wallet(Long ownerId){
        this.ownerId = ownerId;
        this.balance = BigDecimal.ZERO;
    }
}
