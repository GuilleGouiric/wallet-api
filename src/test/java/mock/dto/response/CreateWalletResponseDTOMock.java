package mock.dto.response;

import com.wallet.dto.response.CreateWalletResponseDTO;

import java.time.LocalDate;

public class CreateWalletResponseDTOMock {

    public static CreateWalletResponseDTO getResponse(){

        CreateWalletResponseDTO response = new CreateWalletResponseDTO();
        response.setId(1L);
        response.setOwnerId(123L);
        response.setDateCreated(LocalDate.of(2025,5,12));
        return response;

    }
}
