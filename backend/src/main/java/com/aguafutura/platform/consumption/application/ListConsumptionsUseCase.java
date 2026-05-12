package com.aguafutura.platform.consumption.application;

import com.aguafutura.platform.consumption.application.port.ConsumptionRepositoryPort;
import com.aguafutura.platform.consumption.domain.Consumption;

import java.util.List;
import java.util.UUID;

public class ListConsumptionsUseCase {

    private final ConsumptionRepositoryPort consumptionRepositoryPort;

    public ListConsumptionsUseCase(ConsumptionRepositoryPort consumptionRepositoryPort) {
        this.consumptionRepositoryPort = consumptionRepositoryPort;
    }

    public List<Consumption> execute(UUID tenantId, UUID assetId) {
        return consumptionRepositoryPort.findByTenantIdAndAssetId(tenantId, assetId);
    }
}
