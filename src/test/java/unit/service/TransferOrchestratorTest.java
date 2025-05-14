package unit.service;

import com.wallet.domain.Wallet;
import com.wallet.dto.response.TransferResponseDTO;
import com.wallet.enums.TransferType;
import com.wallet.port.out.TransferRepository;
import com.wallet.port.out.WalletRepository;
import com.wallet.service.TransferOrchestrator;
import com.wallet.service.validation.TransferValidator;
import mock.domain.WalletMock;
import mock.dto.request.TransferRequestDTOMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransferOrchestratorTest {

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private TransferValidator transferValidator;

    @InjectMocks
    private TransferOrchestrator transferOrchestrator;

    @Test
    void transfer(){

        Wallet sourceWallet = WalletMock.getDepositSinkWallet();
        Wallet targetWallet = WalletMock.getWallet();

        when(walletRepository.findById(1L)).thenReturn(sourceWallet);
        when(walletRepository.findById(3L)).thenReturn(targetWallet);
        doNothing().when(transferValidator).validateSourceBalance(any(),any());

        TransferResponseDTO actualResponse = transferOrchestrator.transfer(TransferRequestDTOMock.getRequest(), TransferType.TRANSFER);
        sourceWallet.subtractBalance(BigDecimal.TEN);
        targetWallet.addBalance(BigDecimal.TEN);
        TransferResponseDTO expectedResponse = new TransferResponseDTO(sourceWallet,targetWallet);

        assertEquals(expectedResponse,actualResponse);
        verify(walletRepository, times(2)).findById(anyLong());
        verify(transferRepository, times(1)).save(any());


    }
}
