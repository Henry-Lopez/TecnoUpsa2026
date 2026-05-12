package com.aguafutura.platform.analytics.bootstrap;

import com.aguafutura.platform.analytics.application.GetDashboardMetricsUseCase;
import com.aguafutura.platform.analytics.application.port.AnalyticsRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnalyticsApplicationConfig {

    @Bean
    public GetDashboardMetricsUseCase getDashboardMetricsUseCase(AnalyticsRepositoryPort repositoryPort) {
        return new GetDashboardMetricsUseCase(repositoryPort);
    }
}
