package mock.domain;

import com.wallet.domain.Wallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WalletMock {

    public static Wallet getWallet(){
        Wallet wallet = new Wallet();
        wallet.setId(3L);
        wallet.setOwnerId(123L);
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setDateCreated(LocalDateTime.of(2025,5,12,0,0));
        return wallet;
    }

    public static Wallet getWalletWithBalance(BigDecimal balance){
        Wallet wallet = new Wallet();
        wallet.setId(3L);
        wallet.setOwnerId(123L);
        wallet.setBalance(balance);
        wallet.setDateCreated(LocalDateTime.of(2025,5,12,0,0));
        return wallet;
    }

    public static Wallet getDepositSinkWallet(){
        Wallet wallet = new Wallet();
        wallet.setId(1L);
        wallet.setOwnerId(1L);
        wallet.setBalance(new BigDecimal(100000));
        wallet.setDateCreated(LocalDateTime.of(2025,5,12,0,0));
        return wallet;
    }

    public static Wallet getWithdrawSinkWallet(){
        Wallet wallet = new Wallet();
        wallet.setId(2L);
        wallet.setOwnerId(2L);
        wallet.setBalance(new BigDecimal(100000));
        wallet.setDateCreated(LocalDateTime.of(2025,5,12,0,0));
        return wallet;
    }
}
