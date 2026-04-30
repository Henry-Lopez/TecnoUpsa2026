package com.aguafutura.platform.core.application;

import com.aguafutura.platform.core.application.port.TenantRepositoryPort;
import com.aguafutura.platform.core.domain.Tenant;

import java.util.List;

public class ListTenantsUseCase {

    private final TenantRepositoryPort tenantRepositoryPort;

    public ListTenantsUseCase(TenantRepositoryPort tenantRepositoryPort) {
        this.tenantRepositoryPort = tenantRepositoryPort;
    }

    public List<Tenant> execute() {
        return tenantRepositoryPort.findAll();
    }
}