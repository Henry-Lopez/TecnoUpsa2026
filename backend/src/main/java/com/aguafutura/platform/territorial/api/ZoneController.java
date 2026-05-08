package com.aguafutura.platform.territorial.api;

import com.aguafutura.platform.territorial.application.CreateZoneUseCase;
import com.aguafutura.platform.territorial.application.ListZonesUseCase;
import com.aguafutura.platform.territorial.domain.Zone;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/zones")
public class ZoneController {

    private final CreateZoneUseCase createZoneUseCase;
    private final ListZonesUseCase listZonesUseCase;

    public ZoneController(
            CreateZoneUseCase createZoneUseCase,
            ListZonesUseCase listZonesUseCase
    ) {
        this.createZoneUseCase = createZoneUseCase;
        this.listZonesUseCase = listZonesUseCase;
    }

    @PostMapping
    public ResponseEntity<ZoneResponse> create(
            @RequestBody CreateZoneRequest request,
            Authentication authentication
    ) {
        UUID tenantId = UUID.fromString(authentication.getDetails().toString());

        Zone zone = createZoneUseCase.execute(
                tenantId,
                request.code(),
                request.name()
        );

        ZoneResponse response = toResponse(zone);

        return ResponseEntity
                .created(URI.create("/api/v1/zones/" + response.id()))
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ZoneResponse>> list(Authentication authentication) {
        UUID tenantId = UUID.fromString(authentication.getDetails().toString());

        List<ZoneResponse> zones = listZonesUseCase.execute(tenantId)
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(zones);
    }

    private ZoneResponse toResponse(Zone zone) {
        return new ZoneResponse(
                zone.getId(),
                zone.getTenantId(),
                zone.getCode(),
                zone.getName(),
                zone.getEnabled(),
                zone.getCreatedAt()
        );
    }
}