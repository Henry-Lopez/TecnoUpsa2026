package com.aguafutura.platform.consumption.api;

import com.aguafutura.platform.consumption.application.ListConsumptionsUseCase;
import com.aguafutura.platform.consumption.application.RegisterConsumptionUseCase;
import com.aguafutura.platform.consumption.domain.Consumption;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/consumptions")
public class ConsumptionController {

    private final RegisterConsumptionUseCase registerConsumptionUseCase;
    private final ListConsumptionsUseCase listConsumptionsUseCase;

    public ConsumptionController(
            RegisterConsumptionUseCase registerConsumptionUseCase,
            ListConsumptionsUseCase listConsumptionsUseCase
    ) {
        this.registerConsumptionUseCase = registerConsumptionUseCase;
        this.listConsumptionsUseCase = listConsumptionsUseCase;
    }

    @PostMapping
    public ResponseEntity<ConsumptionResponse> register(
            @RequestBody RegisterConsumptionRequest request,
            Authentication authentication
    ) {
        UUID tenantId = UUID.fromString(authentication.getDetails().toString());

        Consumption consumption = registerConsumptionUseCase.execute(
                tenantId,
                request.assetId(),
                request.readingDate(),
                request.value(),
                request.unit()
        );

        ConsumptionResponse response = toResponse(consumption);

        return ResponseEntity
                .created(URI.create("/api/v1/consumptions/" + response.id()))
                .body(response);
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<ConsumptionResponse>> listByAsset(
            @PathVariable UUID assetId,
            Authentication authentication
    ) {
        UUID tenantId = UUID.fromString(authentication.getDetails().toString());

        List<ConsumptionResponse> consumptions = listConsumptionsUseCase.execute(tenantId, assetId)
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(consumptions);
    }

    private ConsumptionResponse toResponse(Consumption consumption) {
        return new ConsumptionResponse(
                consumption.getId(),
                consumption.getTenantId(),
                consumption.getAssetId(),
                consumption.getReadingDate(),
                consumption.getValue(),
                consumption.getUnit(),
                consumption.getCreatedAt()
        );
    }
}
