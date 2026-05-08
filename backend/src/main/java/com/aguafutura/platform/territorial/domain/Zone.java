package com.aguafutura.platform.territorial.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Zone {

    private UUID id;
    private UUID tenantId;
    private String code;
    private String name;
    private Boolean enabled;
    private LocalDateTime createdAt;

    public Zone(
            UUID id,
            UUID tenantId,
            String code,
            String name,
            Boolean enabled,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.tenantId = tenantId;
        this.code = code;
        this.name = name;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}