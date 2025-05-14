package integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.WalletServiceApplication;
import mock.dto.request.CreateWalletRequestDTOMock;
import mock.dto.request.MovementRequestDTOMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WalletServiceApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/sql/db.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create_whenOwnerExists() throws Exception{
        mockMvc.perform(post("/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CreateWalletRequestDTOMock.getCreateRequest(1L))))
                .andExpect(status().isBadRequest());

    }

    @Test
    void create_validCase() throws Exception{
        mockMvc.perform(post("/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CreateWalletRequestDTOMock.getCreateRequest(6L))))
                .andExpect(status().isOk());

    }

    @Test
    void deposit_walletNotExist() throws Exception{
        mockMvc.perform(post("/wallet/7/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MovementRequestDTOMock.getRequest(BigDecimal.TEN))))
                .andExpect(status().isNotFound());

    }

    @Test
    void deposit_validCase() throws Exception{
        mockMvc.perform(post("/wallet/3/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MovementRequestDTOMock.getRequest(BigDecimal.TEN))))
                .andExpect(status().isOk());

    }

    @Test
    void withdraw_walletNotExist() throws Exception{
        mockMvc.perform(post("/wallet/7/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MovementRequestDTOMock.getRequest(BigDecimal.TEN))))
                .andExpect(status().isNotFound());

    }

    @Test
    void withdraw_insufficientBalance() throws Exception{
        mockMvc.perform(post("/wallet/3/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MovementRequestDTOMock.getRequest(new BigDecimal(1000)))))
                .andExpect(status().isConflict());

    }


    @Test
    void withdraw_validCase() throws Exception{
        mockMvc.perform(post("/wallet/1/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MovementRequestDTOMock.getRequest(BigDecimal.TEN))))
                .andExpect(status().isOk());

    }


}
