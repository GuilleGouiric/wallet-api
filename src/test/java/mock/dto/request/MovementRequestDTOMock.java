package mock.dto.request;

import com.wallet.dto.request.MovementRequestDTO;

import java.math.BigDecimal;

public class MovementRequestDTOMock {

    public static MovementRequestDTO getRequest(BigDecimal amount){
        MovementRequestDTO request = new MovementRequestDTO();
        request.setAmount(amount);
        return request;
    }
}
