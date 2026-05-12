package com.aguafutura.platform.analytics.api;

import com.aguafutura.platform.analytics.application.GetDashboardMetricsUseCase;
import com.aguafutura.platform.analytics.domain.DashboardMetrics;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {

    private final GetDashboardMetricsUseCase getDashboardMetricsUseCase;

    public AnalyticsController(GetDashboardMetricsUseCase getDashboardMetricsUseCase) {
        this.getDashboardMetricsUseCase = getDashboardMetricsUseCase;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardMetrics> getDashboard(Authentication authentication) {
        UUID tenantId = UUID.fromString(authentication.getDetails().toString());
        DashboardMetrics metrics = getDashboardMetricsUseCase.execute(tenantId);
        return ResponseEntity.ok(metrics);
    }
}
