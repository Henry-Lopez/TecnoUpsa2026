CREATE TABLE consumption_record (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL REFERENCES tenant(id),
    asset_id UUID NOT NULL REFERENCES water_asset(id),
    reading_date TIMESTAMP NOT NULL,
    value NUMERIC(19, 4) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_consumption_tenant
    ON consumption_record(tenant_id);

CREATE INDEX idx_consumption_asset
    ON consumption_record(asset_id);

CREATE INDEX idx_consumption_tenant_asset
    ON consumption_record(tenant_id, asset_id);
