package com.wallet.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
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
