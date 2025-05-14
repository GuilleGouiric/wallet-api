package com.wallet.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class CreateWalletRequestDTO {

    @NotNull(message = "Owner Id is required")
    private Long ownerId;

    private String description;

}
