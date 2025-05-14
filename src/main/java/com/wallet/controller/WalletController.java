package com.wallet.controller;

import com.wallet.dto.request.CreateWalletRequestDTO;
import com.wallet.dto.request.MovementRequestDTO;
import com.wallet.dto.response.CreateWalletResponseDTO;
import com.wallet.dto.response.HistoricBalanceResponseDTO;
import com.wallet.port.in.WalletUseCase;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @PostMapping("/{id}/deposit")
    public CreateWalletResponseDTO deposit(
            @Parameter(description = "Wallet Id", required = true) @PathVariable @NotNull(message = "Wallet Id is required") Long id,
            @Parameter(description = "Request body", required = true) @Valid @RequestBody MovementRequestDTO body) {

        return walletService.deposit(body, id);
    }

    @PostMapping("/{id}/withdraw")
    public CreateWalletResponseDTO withdraw(
            @Parameter(description = "Wallet Id", required = true) @PathVariable @NotNull(message = "Wallet Id is required") Long id,
            @Parameter(description = "Request body", required = true) @Valid @RequestBody MovementRequestDTO body) {

        return walletService.withdraw(body, id);
    }

    @GetMapping("/{id}")
    public CreateWalletResponseDTO get(
            @Parameter(description = "Wallet Id", required = true) @PathVariable @NotNull(message = "Wallet Id is required") Long id
           ) {

        return walletService.get(id);
    }

    @GetMapping("/{id}/balance")
    public HistoricBalanceResponseDTO getHistoricalBalance(
            @Parameter(description = "Wallet Id", required = true) @PathVariable @NotNull(message = "Wallet Id is required") Long id,
            @Parameter(description = "historical date", required = true)
            @RequestParam @NotNull(message = "Historic date is required") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime date
    ) {

        return walletService.getHistoricalBalance(id,date);
    }

}
