package com.wallet.controller;

import com.wallet.dto.request.MovementRequestDTO;
import com.wallet.dto.request.TransferRequestDTO;
import com.wallet.dto.response.TransferResponseDTO;
import com.wallet.enums.TransferType;
import com.wallet.port.in.TransferUseCase;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferUseCase transferOrchestrator;

    @PostMapping
    public TransferResponseDTO transfer(
            @Parameter(description = "Request body", required = true) @Valid @RequestBody TransferRequestDTO body) {

        return transferOrchestrator.transfer(body, TransferType.TRANSFER);
    }
}
