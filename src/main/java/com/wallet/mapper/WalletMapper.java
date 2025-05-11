package com.wallet.mapper;

import com.wallet.domain.Wallet;
import com.wallet.dto.response.CreateWalletResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    CreateWalletResponseDTO toResponse(Wallet wallet);

}
