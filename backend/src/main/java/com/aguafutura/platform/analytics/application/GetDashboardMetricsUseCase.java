package com.aguafutura.platform.analytics.application;

import com.aguafutura.platform.analytics.application.port.AnalyticsRepositoryPort;
import com.aguafutura.platform.analytics.domain.DashboardMetrics;

import java.util.UUID;

public class GetDashboardMetricsUseCase {

    private final AnalyticsRepositoryPort repository;

    public GetDashboardMetricsUseCase(AnalyticsRepositoryPort repository) {
        this.repository = repository;
    }

    public DashboardMetrics execute(UUID tenantId) {
        return repository.getMetricsForTenant(tenantId);
    }
}
