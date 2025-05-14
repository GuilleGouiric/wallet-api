package unit.service.validation;

import com.wallet.exception.ApiErrorException;
import com.wallet.port.out.WalletRepository;
import com.wallet.service.validation.WalletValidator;
import mock.domain.WalletMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WalletValidatorTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletValidator walletValidator;

    @Test
    public void ownerAlreadyHasWallet(){

        when(walletRepository.findByOwnerId(anyLong())).thenReturn(Optional.of(WalletMock.getWallet()));
        assertThrows(ApiErrorException.class, () -> walletValidator.ownerAlreadyHasWallet(123L));
        verify(walletRepository,times(1)).findByOwnerId(anyLong());

    }

    @Test
    public void ownerDontHaveWallet(){

        when(walletRepository.findByOwnerId(anyLong())).thenReturn(Optional.empty());
        assertDoesNotThrow( () -> walletValidator.ownerAlreadyHasWallet(123L));
        verify(walletRepository,times(1)).findByOwnerId(anyLong());

    }

    @Test
    public void HistoricDateBeforeWalletCreationDate(){
        LocalDateTime historicDate = LocalDateTime.of(2025,5,12,0,0);
        LocalDateTime walletCreationDate = LocalDateTime.of(2026,5,12,0,0);
        assertThrows(ApiErrorException.class, () -> walletValidator.validateHistoricalDate(walletCreationDate,historicDate));

    }

    @Test
    public void HistoricDateAfterWalletCreationDate(){

        LocalDateTime historicDate = LocalDateTime.of(2026,5,12,0,0);
        LocalDateTime walletCreationDate = LocalDateTime.of(2025,5,12,0,0);
        assertDoesNotThrow( () -> walletValidator.validateHistoricalDate(walletCreationDate,historicDate));

    }

}