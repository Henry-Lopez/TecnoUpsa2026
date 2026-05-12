package com.aguafutura.platform.consumption.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConsumptionJpaRepository extends JpaRepository<ConsumptionJpaEntity, UUID> {
    List<ConsumptionJpaEntity> findByTenantIdAndAssetId(UUID tenantId, UUID assetId);
}
