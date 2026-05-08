package com.aguafutura.platform.assets.application;

import com.aguafutura.platform.assets.application.port.AssetRepositoryPort;
import com.aguafutura.platform.assets.domain.Asset;

import java.util.List;
import java.util.UUID;

public class ListAssetsUseCase {

    private final AssetRepositoryPort assetRepositoryPort;

    public ListAssetsUseCase(AssetRepositoryPort assetRepositoryPort) {
        this.assetRepositoryPort = assetRepositoryPort;
    }

    public List<Asset> execute(UUID tenantId) {
        return assetRepositoryPort.findByTenantId(tenantId);
    }
}