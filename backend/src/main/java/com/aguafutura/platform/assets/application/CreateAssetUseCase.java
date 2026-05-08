package com.aguafutura.platform.assets.application;

import com.aguafutura.platform.assets.application.port.AssetRepositoryPort;
import com.aguafutura.platform.assets.domain.Asset;
import com.aguafutura.platform.assets.domain.AssetType;

import java.util.UUID;

public class CreateAssetUseCase {

    private final AssetRepositoryPort assetRepositoryPort;

    public CreateAssetUseCase(AssetRepositoryPort assetRepositoryPort) {
        this.assetRepositoryPort = assetRepositoryPort;
    }

    public Asset execute(
            UUID tenantId,
            UUID zoneId,
            String code,
            String name,
            AssetType type,
            String locationDescription
    ) {
        Asset asset = Asset.create(
                tenantId,
                zoneId,
                code,
                name,
                type,
                locationDescription
        );

        return assetRepositoryPort.save(asset);
    }
}