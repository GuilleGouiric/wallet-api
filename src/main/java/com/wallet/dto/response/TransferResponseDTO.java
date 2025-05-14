package com.wallet.dto.response;

import com.wallet.domain.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferResponseDTO {

    private Wallet source;
    private Wallet target;

}
