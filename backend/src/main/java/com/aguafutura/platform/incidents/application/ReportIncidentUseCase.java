package com.aguafutura.platform.incidents.application;

import com.aguafutura.platform.incidents.application.port.IncidentRepositoryPort;
import com.aguafutura.platform.incidents.domain.Incident;
import com.aguafutura.platform.incidents.domain.IncidentSeverity;

import java.util.UUID;

public class ReportIncidentUseCase {

    private final IncidentRepositoryPort incidentRepositoryPort;

    public ReportIncidentUseCase(IncidentRepositoryPort incidentRepositoryPort) {
        this.incidentRepositoryPort = incidentRepositoryPort;
    }

    public Incident execute(
            UUID tenantId,
            UUID assetId,
            String title,
            String description,
            IncidentSeverity severity
    ) {
        Incident incident = Incident.report(tenantId, assetId, title, description, severity);
        return incidentRepositoryPort.save(incident);
    }
}
