package unit.adapter;

import com.wallet.adapter.WalletJpaRepositoryAdapter;
import com.wallet.adapter.repository.sql.WalletJpaRepository;

import com.wallet.adapter.repository.sql.entity.WalletEntity;
import com.wallet.adapter.repository.sql.mapper.WalletEntityMapper;
import com.wallet.domain.Wallet;
import com.wallet.exception.ApiErrorException;
import mock.domain.WalletMock;
import mock.entity.WalletEntityMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WalletJpaRepositoryAdapterTest {

    @Mock
    private WalletJpaRepository repository;

    private final WalletEntityMapper mapper = Mappers.getMapper(WalletEntityMapper.class);
    private WalletJpaRepositoryAdapter walletRepository;

    @BeforeEach
    void setup(){
        walletRepository = new WalletJpaRepositoryAdapter(repository,mapper);
    }

    @Test
    public void testFindByOwnerIdWalletFound(){

        when(repository.findByOwnerId(anyLong())).thenReturn(Optional.of(WalletEntityMock.getEntity()));
        Optional<Wallet> actualWallet = walletRepository.findByOwnerId(123L);
        assertEquals(actualWallet.get(), WalletMock.getWallet());

        verify(repository, times(1)).findByOwnerId(anyLong());
    }

    @Test
    public void testFindByOwnerIdWalletNotFound(){

        when(repository.findByOwnerId(anyLong())).thenReturn(Optional.empty());
        Optional<Wallet> actualWallet = walletRepository.findByOwnerId(123L);
        assertEquals(actualWallet, Optional.empty());

        verify(repository, times(1)).findByOwnerId(anyLong());
    }

    @Test
    public void testSave(){

        when(repository.save(any(WalletEntity.class))).thenReturn(WalletEntityMock.getEntity());
        Wallet actualWallet = walletRepository.save(WalletMock.getWallet());
        assertEquals(actualWallet, WalletMock.getWallet());

        verify(repository, times(1)).save(any(WalletEntity.class));
    }

    @Test
    public void testFindByIdWalletFound(){

        when(repository.findById(anyLong())).thenReturn(Optional.of(WalletEntityMock.getEntity()));
        Wallet actualWallet = walletRepository.findById(123L);
        assertEquals(actualWallet, WalletMock.getWallet());

        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    public void testFindByIdWalletNotFound(){

        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ApiErrorException.class, () -> walletRepository.findById(123L));
        verify(repository, times(1)).findById(anyLong());
    }
}
