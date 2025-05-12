package com.wallet.adapter;

import com.wallet.adapter.repository.sql.WalletJpaRepository;
import com.wallet.adapter.repository.sql.entity.WalletEntity;
import com.wallet.adapter.repository.sql.mapper.WalletEntityMapper;
import com.wallet.domain.Wallet;
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

}
