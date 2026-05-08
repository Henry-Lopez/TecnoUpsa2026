package com.aguafutura.platform.assets.infrastructure.persistence.jpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "water_asset")
public class AssetJpaEntity {

    @Id
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "zone_id", nullable = false)
    private UUID zoneId;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(name = "location_description")
    private String locationDescription;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected AssetJpaEntity() {
    }

    public AssetJpaEntity(
            UUID id,
            UUID tenantId,
            UUID zoneId,
            String code,
            String name,
            String type,
            String locationDescription,
            Boolean enabled,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.tenantId = tenantId;
        this.zoneId = zoneId;
        this.code = code;
        this.name = name;
        this.type = type;
        this.locationDescription = locationDescription;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public UUID getZoneId() {
        return zoneId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}