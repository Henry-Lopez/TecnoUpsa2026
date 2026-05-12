package com.aguafutura.platform.workorders.application;

import com.aguafutura.platform.workorders.application.port.WorkOrderRepositoryPort;
import com.aguafutura.platform.workorders.domain.WorkOrder;
import com.aguafutura.platform.workorders.domain.WorkOrderPriority;

import java.util.UUID;

public class CreateWorkOrderUseCase {

    private final WorkOrderRepositoryPort repository;

    public CreateWorkOrderUseCase(WorkOrderRepositoryPort repository) {
        this.repository = repository;
    }

    public WorkOrder execute(
            UUID tenantId,
            UUID assetId,
            UUID incidentId,
            String description,
            WorkOrderPriority priority
    ) {
        if (assetId == null) {
            throw new IllegalArgumentException("Asset ID is required to create a Work Order");
        }

        WorkOrder workOrder = WorkOrder.create(
                tenantId,
                assetId,
                incidentId,
                description,
                priority
        );

        return repository.save(workOrder);
    }
}
