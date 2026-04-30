package com.aguafutura.platform.core.api;

import java.util.UUID;

public record TenantResponse(
        UUID id,
        String code,
        String name,
        String status
) {
}