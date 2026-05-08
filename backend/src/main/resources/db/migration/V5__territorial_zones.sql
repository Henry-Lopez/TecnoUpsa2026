CREATE TABLE territorial_zone (
                                  id UUID PRIMARY KEY,
                                  tenant_id UUID NOT NULL REFERENCES tenant(id),
                                  code VARCHAR(50) NOT NULL,
                                  name VARCHAR(150) NOT NULL,
                                  enabled BOOLEAN NOT NULL DEFAULT TRUE,
                                  created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_territorial_zone_tenant
    ON territorial_zone(tenant_id);