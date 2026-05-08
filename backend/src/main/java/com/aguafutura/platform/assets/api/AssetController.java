package com.aguafutura.platform.assets.api;

import com.aguafutura.platform.assets.application.CreateAssetUseCase;
import com.aguafutura.platform.assets.application.ListAssetsUseCase;
import com.aguafutura.platform.assets.domain.Asset;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    private final CreateAssetUseCase createAssetUseCase;
    private final ListAssetsUseCase listAssetsUseCase;

    public AssetController(
            CreateAssetUseCase createAssetUseCase,
            ListAssetsUseCase listAssetsUseCase
    ) {
        this.createAssetUseCase = createAssetUseCase;
        this.listAssetsUseCase = listAssetsUseCase;
    }

    @PostMapping
    public ResponseEntity<AssetResponse> create(
            @RequestBody CreateAssetRequest request,
            Authentication authentication
    ) {
        UUID tenantId = UUID.fromString(authentication.getDetails().toString());

        Asset asset = createAssetUseCase.execute(
                tenantId,
                request.zoneId(),
                request.code(),
                request.name(),
                request.type(),
                request.locationDescription()
        );

        AssetResponse response = toResponse(asset);

        return ResponseEntity
                .created(URI.create("/api/v1/assets/" + response.id()))
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<AssetResponse>> list(Authentication authentication) {
        UUID tenantId = UUID.fromString(authentication.getDetails().toString());

        List<AssetResponse> assets = listAssetsUseCase.execute(tenantId)
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(assets);
    }

    private AssetResponse toResponse(Asset asset) {
        return new AssetResponse(
                asset.getId(),
                asset.getTenantId(),
                asset.getZoneId(),
                asset.getCode(),
                asset.getName(),
                asset.getType(),
                asset.getLocationDescription(),
                asset.getEnabled(),
                asset.getCreatedAt()
        );
    }
}