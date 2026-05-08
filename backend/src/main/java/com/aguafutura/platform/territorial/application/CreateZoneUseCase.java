package com.aguafutura.platform.territorial.application;

import com.aguafutura.platform.territorial.application.port.ZoneRepositoryPort;
import com.aguafutura.platform.territorial.domain.Zone;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateZoneUseCase {

    private final ZoneRepositoryPort zoneRepositoryPort;

    public CreateZoneUseCase(ZoneRepositoryPort zoneRepositoryPort) {
        this.zoneRepositoryPort = zoneRepositoryPort;
    }

    public Zone execute(UUID tenantId, String code, String name) {
        Zone zone = new Zone(
                UUID.randomUUID(),
                tenantId,
                code,
                name,
                true,
                LocalDateTime.now()
        );

        return zoneRepositoryPort.save(zone);
    }
}