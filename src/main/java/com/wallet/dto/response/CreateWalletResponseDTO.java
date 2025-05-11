package com.wallet.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateWalletResponseDTO {

    private Long id;
    private Long ownerId;
    private LocalDate dateCreated;
}
