package unit.service.validation;

import com.wallet.exception.ApiErrorException;
import com.wallet.service.validation.TransferValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TransferValidatorTest {


    @InjectMocks
    private TransferValidator transferValidator;

    @Test
    void sourceBalanceInsufficientForTransfer(){
        BigDecimal sourceBalance = BigDecimal.ZERO;
        BigDecimal transferAmount = BigDecimal.TEN;

        assertThrows(ApiErrorException.class, () -> transferValidator.validateSourceBalance(sourceBalance,transferAmount));
    }

    @Test
    void sourceHasBalanceForTransfer(){
        BigDecimal sourceBalance = BigDecimal.TEN;
        BigDecimal transferAmount = BigDecimal.ONE;

        assertDoesNotThrow(() -> transferValidator.validateSourceBalance(sourceBalance,transferAmount));
    }
}
