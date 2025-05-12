package mock.domain;

import com.wallet.domain.Wallet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WalletMock {

    public static Wallet getWallet(){
        Wallet wallet = new Wallet();
        wallet.setId(1L);
        wallet.setOwnerId(123L);
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setDateCreated(LocalDate.of(2025,5,12));
        return wallet;
    }
}
