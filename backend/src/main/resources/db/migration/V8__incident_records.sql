CREATE TABLE incident_record (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL REFERENCES tenant(id),
    asset_id UUID NOT NULL REFERENCES water_asset(id),
    title VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    severity VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_incident_tenant
    ON incident_record(tenant_id);

CREATE INDEX idx_incident_asset
    ON incident_record(asset_id);

CREATE INDEX idx_incident_tenant_asset
    ON incident_record(tenant_id, asset_id);
