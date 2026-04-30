package com.aguafutura.platform.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record Tenant(
        UUID id,
        String code,
        String name,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Integer versionLock
) {
}