package com.aguafutura.platform.iam.api;

import java.util.UUID;

public record LoginRequest(
        UUID tenantId,
        String email,
        String password
) {
}