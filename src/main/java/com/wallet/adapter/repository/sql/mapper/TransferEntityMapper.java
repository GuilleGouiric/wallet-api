package com.wallet.adapter.repository.sql.mapper;

import com.wallet.adapter.repository.sql.entity.TransferEntity;
import com.wallet.domain.Transfer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferEntityMapper {

    TransferEntity toEntity(Transfer transfer);
}
