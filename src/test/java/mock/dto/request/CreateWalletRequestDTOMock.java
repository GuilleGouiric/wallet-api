package mock.dto.request;

import com.wallet.dto.request.CreateWalletRequestDTO;

public class CreateWalletRequestDTOMock {

    public static CreateWalletRequestDTO getCreateRequest(Long ownerId){

        CreateWalletRequestDTO request = new CreateWalletRequestDTO();
        request.setOwnerId(ownerId);
        return request;

    }
}
