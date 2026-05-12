package com.aguafutura.platform.incidents.bootstrap;

import com.aguafutura.platform.incidents.application.ListIncidentsUseCase;
import com.aguafutura.platform.incidents.application.ReportIncidentUseCase;
import com.aguafutura.platform.incidents.application.port.IncidentRepositoryPort;
import com.aguafutura.platform.incidents.infrastructure.persistence.jpa.IncidentJpaRepository;
import com.aguafutura.platform.incidents.infrastructure.persistence.jpa.IncidentPersistenceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IncidentsApplicationConfig {

    @Bean
    public IncidentRepositoryPort incidentRepositoryPort(IncidentJpaRepository repository) {
        return new IncidentPersistenceAdapter(repository);
    }

    @Bean
    public ReportIncidentUseCase reportIncidentUseCase(IncidentRepositoryPort port) {
        return new ReportIncidentUseCase(port);
    }

    @Bean
    public ListIncidentsUseCase listIncidentsUseCase(IncidentRepositoryPort port) {
        return new ListIncidentsUseCase(port);
    }
}
