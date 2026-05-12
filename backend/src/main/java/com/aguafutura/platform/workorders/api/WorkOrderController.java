package com.aguafutura.platform.workorders.api;

import com.aguafutura.platform.workorders.application.CreateWorkOrderUseCase;
import com.aguafutura.platform.workorders.application.ListWorkOrdersUseCase;
import com.aguafutura.platform.workorders.domain.WorkOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/work-orders")
public class WorkOrderController {

    private final CreateWorkOrderUseCase createWorkOrderUseCase;
    private final ListWorkOrdersUseCase listWorkOrdersUseCase;

    public WorkOrderController(
            CreateWorkOrderUseCase createWorkOrderUseCase,
            ListWorkOrdersUseCase listWorkOrdersUseCase
    ) {
        this.createWorkOrderUseCase = createWorkOrderUseCase;
        this.listWorkOrdersUseCase = listWorkOrdersUseCase;
    }

    @PostMapping
    public ResponseEntity<WorkOrderResponse> create(
            @RequestBody CreateWorkOrderRequest request,
            Authentication authentication
    ) {
        UUID tenantId = UUID.fromString(authentication.getDetails().toString());

        WorkOrder workOrder = createWorkOrderUseCase.execute(
                tenantId,
                request.assetId(),
                request.incidentId(),
                request.description(),
                request.priority()
        );

        WorkOrderResponse response = toResponse(workOrder);

        return ResponseEntity
                .created(URI.create("/api/v1/work-orders/" + response.id()))
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<WorkOrderResponse>> list(Authentication authentication) {
        UUID tenantId = UUID.fromString(authentication.getDetails().toString());

        List<WorkOrderResponse> workOrders = listWorkOrdersUseCase.execute(tenantId)
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(workOrders);
    }

    private WorkOrderResponse toResponse(WorkOrder workOrder) {
        return new WorkOrderResponse(
                workOrder.getId(),
                workOrder.getTenantId(),
                workOrder.getAssetId(),
                workOrder.getIncidentId(),
                workOrder.getDescription(),
                workOrder.getStatus(),
                workOrder.getPriority(),
                workOrder.getAssignedTo(),
                workOrder.getScheduledAt(),
                workOrder.getCompletedAt(),
                workOrder.getCreatedAt()
        );
    }
}
