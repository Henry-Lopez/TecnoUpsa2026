package com.aguafutura.platform.ai.api;

import com.aguafutura.platform.ai.application.DetectAnomalyUseCase;
import com.aguafutura.platform.ai.domain.AnomalyReport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ai")
public class AiController {

    private final DetectAnomalyUseCase detectAnomalyUseCase;

    public AiController(DetectAnomalyUseCase detectAnomalyUseCase) {
        this.detectAnomalyUseCase = detectAnomalyUseCase;
    }

    @GetMapping("/analyze/{assetId}")
    public ResponseEntity<AnomalyReport> analyzeAsset(@PathVariable UUID assetId, Authentication authentication) {
        UUID tenantId = UUID.fromString(authentication.getDetails().toString());
        AnomalyReport report = detectAnomalyUseCase.execute(tenantId, assetId);
        return ResponseEntity.ok(report);
    }
}
