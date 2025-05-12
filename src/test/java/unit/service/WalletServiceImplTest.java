package unit.service;


import com.wallet.domain.Wallet;
import com.wallet.dto.response.CreateWalletResponseDTO;
import com.wallet.exception.ApiErrorException;
import com.wallet.mapper.WalletMapper;
import com.wallet.port.out.WalletRepository;
import com.wallet.service.WalletServiceImpl;
import mock.domain.WalletMock;
import mock.dto.request.CreateWalletRequestDTOMock;
import mock.dto.response.CreateWalletResponseDTOMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WalletServiceImplTest {

    @Mock
    private WalletRepository walletRepository;

    private final WalletMapper mapper = Mappers.getMapper(WalletMapper.class);
    private WalletServiceImpl walletService;

    @BeforeEach
    void setup(){
        walletService = new WalletServiceImpl(walletRepository,mapper);
    }

    @Test
    void createWalletTestValidCase(){

        when(walletRepository.findByOwnerId(anyLong())).thenReturn(Optional.empty());
        when(walletRepository.save(any(Wallet.class))).thenReturn(WalletMock.getWallet());

        CreateWalletResponseDTO actualResponse = walletService.createWallet(CreateWalletRequestDTOMock.getCreateRequest(124L));
        assertEquals(actualResponse, CreateWalletResponseDTOMock.getResponse());

        verify(walletRepository,times(1)).findByOwnerId(anyLong());
        verify(walletRepository,times(1)).save(any(Wallet.class));

    }

    @Test
    void createWalletTestOwnerAlreadyHasAWallet(){

        when(walletRepository.findByOwnerId(anyLong())).thenReturn(Optional.of(WalletMock.getWallet()));

        assertThrows(ApiErrorException.class, () -> walletService.createWallet(CreateWalletRequestDTOMock.getCreateRequest(123L)));

        verify(walletRepository,times(1)).findByOwnerId(anyLong());
        verify(walletRepository,times(0)).save(any(Wallet.class));

    }
}
