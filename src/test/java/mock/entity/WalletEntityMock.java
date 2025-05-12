package mock.entity;

import com.wallet.adapter.repository.sql.entity.WalletEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WalletEntityMock {

    public static WalletEntity getEntity(){
        WalletEntity entity = new WalletEntity();
        entity.setId(1L);
        entity.setOwnerId(123L);
        entity.setBalance(BigDecimal.ZERO);
        entity.setDateCreated(LocalDate.of(2025,5,12));
        return entity;
    }
}
