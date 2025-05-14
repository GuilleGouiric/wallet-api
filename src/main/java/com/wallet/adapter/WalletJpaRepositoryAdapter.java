package com.wallet.adapter;

import com.wallet.adapter.repository.sql.WalletJpaRepository;
import com.wallet.adapter.repository.sql.entity.WalletEntity;
import com.wallet.adapter.repository.sql.mapper.WalletEntityMapper;
import com.wallet.domain.Wallet;
import com.wallet.enums.ApiError;
import com.wallet.exception.ApiErrorException;
import com.wallet.port.out.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WalletJpaRepositoryAdapter implements WalletRepository {

    private final WalletJpaRepository repository;
    private final WalletEntityMapper mapper;

    public Optional<Wallet> findByOwnerId(Long ownerId){
        return repository.findByOwnerId(ownerId)
                .map(mapper::toDomain);
    }

    public Wallet save(Wallet wallet){
        WalletEntity entity = mapper.toEntity(wallet);
        return mapper.toDomain(repository.save(entity));
    }

    public Wallet findById(Long id){
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new ApiErrorException(ApiError.WALLET_NOT_FOUND, String.valueOf(id)));
    }

}
