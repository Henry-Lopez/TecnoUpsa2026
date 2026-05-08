CREATE TABLE water_asset (
                             id UUID PRIMARY KEY,
                             tenant_id UUID NOT NULL REFERENCES tenant(id),
                             zone_id UUID NOT NULL REFERENCES territorial_zone(id),
                             code VARCHAR(50) NOT NULL,
                             name VARCHAR(150) NOT NULL,
                             type VARCHAR(50) NOT NULL,
                             location_description VARCHAR(255),
                             enabled BOOLEAN NOT NULL DEFAULT TRUE,
                             created_at TIMESTAMP NOT NULL DEFAULT NOW(),

                             CONSTRAINT uk_water_asset_tenant_code UNIQUE (tenant_id, code)
);

CREATE INDEX idx_water_asset_tenant
    ON water_asset(tenant_id);

CREATE INDEX idx_water_asset_zone
    ON water_asset(zone_id);

CREATE INDEX idx_water_asset_tenant_zone
    ON water_asset(tenant_id, zone_id);