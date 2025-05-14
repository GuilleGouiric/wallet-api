package mock.dto.response;

import com.wallet.dto.response.TransferResponseDTO;
import mock.domain.WalletMock;

import java.math.BigDecimal;

public class TransferResponseDTOMock {

    public static TransferResponseDTO getDepositResponse(){
        return new  TransferResponseDTO(WalletMock.getDepositSinkWallet(), WalletMock.getWalletWithBalance(BigDecimal.TEN));
    }

    public static TransferResponseDTO getWithdrawResponse(){
        return new TransferResponseDTO(WalletMock.getWallet(), WalletMock.getWithdrawSinkWallet());
    }
}
