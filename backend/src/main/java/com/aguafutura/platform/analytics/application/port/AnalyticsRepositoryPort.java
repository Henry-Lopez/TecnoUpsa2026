package com.aguafutura.platform.analytics.application.port;

import com.aguafutura.platform.analytics.domain.DashboardMetrics;

import java.util.UUID;

public interface AnalyticsRepositoryPort {
    DashboardMetrics getMetricsForTenant(UUID tenantId);
}
