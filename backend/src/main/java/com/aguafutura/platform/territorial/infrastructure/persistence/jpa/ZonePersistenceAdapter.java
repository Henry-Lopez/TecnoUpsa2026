package com.aguafutura.platform.territorial.infrastructure.persistence.jpa;

import com.aguafutura.platform.territorial.application.port.ZoneRepositoryPort;
import com.aguafutura.platform.territorial.domain.Zone;

import java.util.List;
import java.util.UUID;

public class ZonePersistenceAdapter implements ZoneRepositoryPort {

    private final ZoneJpaRepository repository;

    public ZonePersistenceAdapter(ZoneJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Zone save(Zone zone) {

        ZoneJpaEntity entity = new ZoneJpaEntity();

        entity.setId(zone.getId());
        entity.setTenantId(zone.getTenantId());
        entity.setCode(zone.getCode());
        entity.setName(zone.getName());
        entity.setEnabled(zone.getEnabled());
        entity.setCreatedAt(zone.getCreatedAt());

        ZoneJpaEntity saved = repository.save(entity);

        return new Zone(
                saved.getId(),
                saved.getTenantId(),
                saved.getCode(),
                saved.getName(),
                saved.getEnabled(),
                saved.getCreatedAt()
        );
    }

    @Override
    public List<Zone> findByTenantId(UUID tenantId) {

        return repository.findByTenantId(tenantId)
                .stream()
                .map(entity -> new Zone(
                        entity.getId(),
                        entity.getTenantId(),
                        entity.getCode(),
                        entity.getName(),
                        entity.getEnabled(),
                        entity.getCreatedAt()
                ))
                .toList();
    }
}