package com.wallet.adapter.repository.sql.mapper;

import com.wallet.adapter.repository.sql.entity.WalletEntity;
import com.wallet.domain.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletEntityMapper {

    Wallet toDomain(WalletEntity wallet);
    WalletEntity toEntity(Wallet wallet);
}
