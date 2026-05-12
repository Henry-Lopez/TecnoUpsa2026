CREATE TABLE evidence (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL REFERENCES tenant(id),
    reference_type VARCHAR(50) NOT NULL,
    reference_id UUID NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    content_type VARCHAR(100) NOT NULL,
    file_path VARCHAR(1024) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_evidence_tenant
    ON evidence(tenant_id);

CREATE INDEX idx_evidence_reference
    ON evidence(reference_type, reference_id);
