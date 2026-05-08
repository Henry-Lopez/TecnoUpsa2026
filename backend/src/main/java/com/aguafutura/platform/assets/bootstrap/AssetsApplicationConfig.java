package com.aguafutura.platform.assets.bootstrap;

import com.aguafutura.platform.assets.application.CreateAssetUseCase;
import com.aguafutura.platform.assets.application.ListAssetsUseCase;
import com.aguafutura.platform.assets.application.port.AssetRepositoryPort;
import com.aguafutura.platform.assets.infrastructure.persistence.jpa.AssetJpaRepository;
import com.aguafutura.platform.assets.infrastructure.persistence.jpa.AssetPersistenceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssetsApplicationConfig {

    @Bean
    public AssetRepositoryPort assetRepositoryPort(AssetJpaRepository assetJpaRepository) {
        return new AssetPersistenceAdapter(assetJpaRepository);
    }

    @Bean
    public CreateAssetUseCase createAssetUseCase(AssetRepositoryPort assetRepositoryPort) {
        return new CreateAssetUseCase(assetRepositoryPort);
    }

    @Bean
    public ListAssetsUseCase listAssetsUseCase(AssetRepositoryPort assetRepositoryPort) {
        return new ListAssetsUseCase(assetRepositoryPort);
    }
}