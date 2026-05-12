package com.aguafutura.platform.workorders.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkOrderJpaRepository extends JpaRepository<WorkOrderJpaEntity, UUID> {
    List<WorkOrderJpaEntity> findByTenantIdOrderByCreatedAtDesc(UUID tenantId);
    List<WorkOrderJpaEntity> findByAssetIdOrderByCreatedAtDesc(UUID assetId);
}
