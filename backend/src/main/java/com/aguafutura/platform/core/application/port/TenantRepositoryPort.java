package com.aguafutura.platform.core.application.port;

import com.aguafutura.platform.core.domain.Tenant;

import java.util.List;

public interface TenantRepositoryPort {

    List<Tenant> findAll();
}