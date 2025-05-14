package com.wallet.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Wallet {

    private Long id;
    private Long ownerId;
    private BigDecimal balance;
    private String description;
    private LocalDateTime dateCreated;

    public Wallet(Long ownerId, String description){
        this.ownerId = ownerId;
        this.description = description;
        this.balance = BigDecimal.ZERO;
    }

    public void addBalance(BigDecimal amount){
        balance = balance.add(amount);
    }

    public void subtractBalance(BigDecimal amount){
        balance = balance.subtract(amount);
    }
}
