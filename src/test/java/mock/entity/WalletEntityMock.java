package mock.entity;

import com.wallet.adapter.repository.sql.entity.WalletEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WalletEntityMock {

    public static WalletEntity getEntity(){
        WalletEntity entity = new WalletEntity();
        entity.setId(3L);
        entity.setOwnerId(123L);
        entity.setBalance(BigDecimal.ZERO);
        entity.setDateCreated(LocalDateTime.of(2025,5,12,0,0));
        return entity;
    }
}
