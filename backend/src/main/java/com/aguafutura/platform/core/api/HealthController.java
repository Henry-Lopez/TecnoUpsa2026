package com.aguafutura.platform.core.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/api/v1/health")
    public Map<String, Object> health() {
        return Map.of(
                "status", "UP",
                "service", "AguaFutura AI Backend",
                "module", "core-platform",
                "timestamp", Instant.now().toString()
        );
    }
}