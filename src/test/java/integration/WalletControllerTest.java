package integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.WalletServiceApplication;
import mock.dto.request.CreateWalletRequestDTOMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WalletServiceApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/sql/insert_wallet.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenOwnerExists() throws Exception{
        mockMvc.perform(post("/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CreateWalletRequestDTOMock.getCreateRequest(123L))))
                .andExpect(status().isBadRequest());

    }
}
