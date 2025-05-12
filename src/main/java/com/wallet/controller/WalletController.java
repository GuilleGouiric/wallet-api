package com.wallet.controller;

import com.wallet.dto.request.CreateWalletRequestDTO;
import com.wallet.dto.response.CreateWalletResponseDTO;
import com.wallet.port.in.WalletUseCase;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletUseCase walletService;

    @PostMapping
    public CreateWalletResponseDTO createWallet(
            @Parameter(description = "Request body", required = true) @Valid @RequestBody CreateWalletRequestDTO body) {
        return walletService.createWallet(body);
    }

}
