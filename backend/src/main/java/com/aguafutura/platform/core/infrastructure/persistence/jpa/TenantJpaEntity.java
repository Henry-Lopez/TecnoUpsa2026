package com.aguafutura.platform.core.infrastructure.persistence.jpa;

import com.aguafutura.platform.core.domain.Tenant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tenant")
public class TenantJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true, length = 80)
    private String code;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Version
    @Column(name = "version_lock", nullable = false)
    private Integer versionLock;

    protected TenantJpaEntity() {
    }

    public Tenant toDomain() {
        return new Tenant(
                id,
                code,
                name,
                status,
                createdAt,
                updatedAt,
                versionLock
        );
    }
}