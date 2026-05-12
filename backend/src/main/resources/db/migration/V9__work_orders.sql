CREATE TABLE work_order (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL REFERENCES tenant(id),
    asset_id UUID NOT NULL REFERENCES water_asset(id),
    incident_id UUID REFERENCES incident_record(id),
    description TEXT NOT NULL,
    status VARCHAR(50) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    assigned_to VARCHAR(255),
    scheduled_at TIMESTAMP,
    completed_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_work_order_tenant
    ON work_order(tenant_id);

CREATE INDEX idx_work_order_asset
    ON work_order(asset_id);

CREATE INDEX idx_work_order_incident
    ON work_order(incident_id);

CREATE INDEX idx_work_order_status
    ON work_order(status);
