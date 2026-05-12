package com.aguafutura.platform.incidents.api;

import com.aguafutura.platform.incidents.domain.IncidentSeverity;
import java.util.UUID;

public record ReportIncidentRequest(
        UUID assetId,
        String title,
        String description,
        IncidentSeverity severity
) {
}
