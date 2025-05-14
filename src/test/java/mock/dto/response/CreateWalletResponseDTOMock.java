package mock.dto.response;

import com.wallet.dto.response.CreateWalletResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateWalletResponseDTOMock {

    public static CreateWalletResponseDTO getResponse(){

        CreateWalletResponseDTO response = new CreateWalletResponseDTO();
        response.setId(3L);
        response.setOwnerId(123L);
        response.setDateCreated(LocalDateTime.of(2025,5,12,0,0));
        response.setBalance(BigDecimal.ZERO);
        return response;

    }
}
