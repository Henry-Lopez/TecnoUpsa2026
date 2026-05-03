package com.aguafutura.platform.iam.api;

public record AuthResponse(
        String accessToken,
        String tokenType
) {
}