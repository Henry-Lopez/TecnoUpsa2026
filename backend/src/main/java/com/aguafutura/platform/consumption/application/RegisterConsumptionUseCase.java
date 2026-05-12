package com.aguafutura.platform.consumption.application;

import com.aguafutura.platform.consumption.application.port.ConsumptionRepositoryPort;
import com.aguafutura.platform.consumption.domain.Consumption;
import com.aguafutura.platform.consumption.domain.UnitType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class RegisterConsumptionUseCase {

    private final ConsumptionRepositoryPort consumptionRepositoryPort;

    public RegisterConsumptionUseCase(ConsumptionRepositoryPort consumptionRepositoryPort) {
        this.consumptionRepositoryPort = consumptionRepositoryPort;
    }

    public Consumption execute(
            UUID tenantId,
            UUID assetId,
            LocalDateTime readingDate,
            BigDecimal value,
            UnitType unit
    ) {
        Consumption consumption = Consumption.create(tenantId, assetId, readingDate, value, unit);
        return consumptionRepositoryPort.save(consumption);
    }
}
