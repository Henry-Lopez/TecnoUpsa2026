package com.aguafutura.platform.core.api;

import com.aguafutura.platform.core.application.ListTenantsUseCase;
import com.aguafutura.platform.core.domain.Tenant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TenantController {

    private final ListTenantsUseCase listTenantsUseCase;

    public TenantController(ListTenantsUseCase listTenantsUseCase) {
        this.listTenantsUseCase = listTenantsUseCase;
    }

    @GetMapping("/api/v1/tenants")
    public List<TenantResponse> listTenants() {
        return listTenantsUseCase.execute()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private TenantResponse toResponse(Tenant tenant) {
        return new TenantResponse(
                tenant.id(),
                tenant.code(),
                tenant.name(),
                tenant.status()
        );
    }
}