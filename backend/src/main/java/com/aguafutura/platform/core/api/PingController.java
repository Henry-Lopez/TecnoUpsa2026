package com.aguafutura.platform.core.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class PingController {

    @GetMapping("/api/v1/ping")
    public Map<String, Object> ping(HttpServletRequest request) {
        Object correlationId = request.getAttribute("correlationId");

        return Map.of(
                "message", "pong",
                "service", "AguaFutura AI Backend",
                "correlationId", correlationId != null ? correlationId : "not-found",
                "timestamp", Instant.now().toString()
        );
    }
}