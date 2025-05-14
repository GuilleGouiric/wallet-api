package unit.service;


import com.wallet.domain.Wallet;
import com.wallet.dto.response.CreateWalletResponseDTO;
import com.wallet.dto.response.HistoricBalanceResponseDTO;
import com.wallet.enums.ApiError;
import com.wallet.enums.TransferType;
import com.wallet.exception.ApiErrorException;
import com.wallet.mapper.WalletMapper;
import com.wallet.port.in.TransferUseCase;
import com.wallet.port.out.WalletRepository;
import com.wallet.service.WalletServiceImpl;
import com.wallet.service.validation.WalletValidator;
import mock.domain.WalletMock;
import mock.dto.request.CreateWalletRequestDTOMock;
import mock.dto.request.MovementRequestDTOMock;
import mock.dto.response.CreateWalletResponseDTOMock;
import mock.dto.response.TransferResponseDTOMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WalletServiceImplTest {

    @Mock
    private WalletRepository walletRepository;
    @Mock
    private TransferUseCase transferUseCase;
    @Mock
    private WalletValidator validator;

    private final WalletMapper mapper = Mappers.getMapper(WalletMapper.class);
    private WalletServiceImpl walletService;

    @BeforeEach
    void setup(){
        walletService = new WalletServiceImpl(walletRepository, mapper,validator, transferUseCase);
    }

    @Test
    void createWalletTestValidCase(){

        doNothing().when(validator).ownerAlreadyHasWallet(anyLong());
        when(walletRepository.save(any(Wallet.class))).thenReturn(WalletMock.getWallet());

        CreateWalletResponseDTO actualResponse = walletService.createWallet(CreateWalletRequestDTOMock.getCreateRequest(124L));
        assertEquals(actualResponse, CreateWalletResponseDTOMock.getResponse());

        verify(validator,times(1)).ownerAlreadyHasWallet(anyLong());
        verify(walletRepository,times(1)).save(any(Wallet.class));

    }

    @Test
    void createWalletTestOwnerAlreadyHasAWallet(){

        doThrow( new ApiErrorException(ApiError.OWNER_ALREADY_HAS_WALLET,String.valueOf(123L))).when(validator).ownerAlreadyHasWallet(anyLong());

        assertThrows(ApiErrorException.class, () -> walletService.createWallet(CreateWalletRequestDTOMock.getCreateRequest(123L)));

        verify(validator,times(1)).ownerAlreadyHasWallet(anyLong());
        verify(walletRepository,times(0)).save(any(Wallet.class));

    }

    @Test
    void depositValidCase(){

        when(transferUseCase.transfer(any(), any())).thenReturn(TransferResponseDTOMock.getDepositResponse());
        CreateWalletResponseDTO actualResponse = walletService.deposit(MovementRequestDTOMock.getRequest(BigDecimal.TEN), 123L);
        CreateWalletResponseDTO expectedResponse = CreateWalletResponseDTOMock.getResponse();
        expectedResponse.setBalance(BigDecimal.TEN);
        assertEquals(expectedResponse,actualResponse);

        verify(transferUseCase, times(1)).transfer(any(), any());

    }

    @Test
    void withdrawValidCase(){

        when(transferUseCase.transfer(any(), eq(TransferType.WITHDRAW))).thenReturn(TransferResponseDTOMock.getWithdrawResponse());
        CreateWalletResponseDTO actualResponse = walletService.withdraw(MovementRequestDTOMock.getRequest(BigDecimal.TEN), 123L);
        CreateWalletResponseDTO expectedResponse = CreateWalletResponseDTOMock.getResponse();
        assertEquals(expectedResponse, actualResponse);

        verify(transferUseCase, times(1)).transfer(any(), eq(TransferType.WITHDRAW));

    }

    @Test
    void getHistoricValidCase(){

        when(walletRepository.findById(anyLong())).thenReturn(WalletMock.getWallet());
        doNothing().when(validator).validateHistoricalDate(any(), any());
        when(transferUseCase.getHistoricalBalance(anyLong(),any())).thenReturn(BigDecimal.TEN);

        LocalDateTime date = (LocalDateTime.of(2025,8,12,0,0));

        HistoricBalanceResponseDTO expectedResponse = HistoricBalanceResponseDTO
                .builder()
                .balanceDate(date)
                .walletId(3L)
                .balance(BigDecimal.TEN)
                .build();

        HistoricBalanceResponseDTO actualResponse = walletService.getHistoricalBalance(3L, date);
        assertEquals(expectedResponse,actualResponse);

        verify(walletRepository,times(1)).findById(anyLong());
        verify(validator,times(1)).validateHistoricalDate(any(),any());
        verify(transferUseCase, times(1)).getHistoricalBalance(anyLong(), any());
    }
}
