package com.aguafutura.platform.territorial.application.port;

import com.aguafutura.platform.territorial.domain.Zone;

import java.util.List;
import java.util.UUID;

public interface ZoneRepositoryPort {

    Zone save(Zone zone);

    List<Zone> findByTenantId(UUID tenantId);
}