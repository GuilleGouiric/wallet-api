package mock.dto.request;

import com.wallet.dto.request.TransferRequestDTO;

import java.math.BigDecimal;


public class TransferRequestDTOMock {

    public static TransferRequestDTO getRequest(){
        return new TransferRequestDTO(1L,3L, BigDecimal.TEN);
    }
}
