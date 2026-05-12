package com.aguafutura.platform.consumption.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "consumption_record")
public class ConsumptionJpaEntity {

    @Id
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "asset_id", nullable = false)
    private UUID assetId;

    @Column(name = "reading_date", nullable = false)
    private LocalDateTime readingDate;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal value;

    @Column(nullable = false)
    private String unit;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected ConsumptionJpaEntity() {
    }

    public ConsumptionJpaEntity(
            UUID id,
            UUID tenantId,
            UUID assetId,
            LocalDateTime readingDate,
            BigDecimal value,
            String unit,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.tenantId = tenantId;
        this.assetId = assetId;
        this.readingDate = readingDate;
        this.value = value;
        this.unit = unit;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public UUID getAssetId() {
        return assetId;
    }

    public LocalDateTime getReadingDate() {
        return readingDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
