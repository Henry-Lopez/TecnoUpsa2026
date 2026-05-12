package com.aguafutura.platform.consumption.api;

import com.aguafutura.platform.consumption.domain.UnitType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterConsumptionRequest(
        UUID assetId,
        LocalDateTime readingDate,
        BigDecimal value,
        UnitType unit
) {
}
