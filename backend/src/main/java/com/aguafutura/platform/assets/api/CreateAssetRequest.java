package com.aguafutura.platform.assets.api;

import com.aguafutura.platform.assets.domain.AssetType;

import java.util.UUID;

public record CreateAssetRequest(
        UUID zoneId,
        String code,
        String name,
        AssetType type,
        String locationDescription
) {
}