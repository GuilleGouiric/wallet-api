package com.wallet.adapter.repository.sql;

import com.wallet.adapter.repository.sql.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletJpaRepository extends JpaRepository<WalletEntity,Long> {

    Optional<WalletEntity> findByOwnerId(Long ownerId);
}
