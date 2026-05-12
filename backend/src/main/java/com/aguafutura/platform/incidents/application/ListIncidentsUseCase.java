package com.aguafutura.platform.incidents.application;

import com.aguafutura.platform.incidents.application.port.IncidentRepositoryPort;
import com.aguafutura.platform.incidents.domain.Incident;

import java.util.List;
import java.util.UUID;

public class ListIncidentsUseCase {

    private final IncidentRepositoryPort incidentRepositoryPort;

    public ListIncidentsUseCase(IncidentRepositoryPort incidentRepositoryPort) {
        this.incidentRepositoryPort = incidentRepositoryPort;
    }

    public List<Incident> execute(UUID tenantId) {
        return incidentRepositoryPort.findByTenantId(tenantId);
    }
}
