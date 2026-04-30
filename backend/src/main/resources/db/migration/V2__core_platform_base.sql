CREATE TABLE IF NOT EXISTS tenant (
                                      id UUID PRIMARY KEY,
                                      code VARCHAR(80) NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    version_lock INT NOT NULL DEFAULT 0
    );

CREATE TABLE IF NOT EXISTS audit_log (
                                         id UUID PRIMARY KEY,
                                         tenant_id UUID REFERENCES tenant(id),
    actor_id UUID,
    actor_role VARCHAR(80),
    action VARCHAR(120) NOT NULL,
    resource_type VARCHAR(120) NOT NULL,
    resource_id VARCHAR(120),
    reason TEXT,
    before_hash TEXT,
    after_hash TEXT,
    correlation_id VARCHAR(120),
    ip_address VARCHAR(80),
    user_agent TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT now()
    );

CREATE TABLE IF NOT EXISTS domain_event_outbox (
                                                   id UUID PRIMARY KEY,
                                                   tenant_id UUID REFERENCES tenant(id),
    aggregate_type VARCHAR(120) NOT NULL,
    aggregate_id VARCHAR(120) NOT NULL,
    event_type VARCHAR(120) NOT NULL,
    payload JSONB NOT NULL,
    status VARCHAR(50) NOT NULL,
    correlation_id VARCHAR(120),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    processed_at TIMESTAMP,
    retry_count INT NOT NULL DEFAULT 0
    );

CREATE TABLE IF NOT EXISTS system_configuration (
                                                    id UUID PRIMARY KEY,
                                                    tenant_id UUID REFERENCES tenant(id),
    config_key VARCHAR(150) NOT NULL,
    config_value TEXT NOT NULL,
    description TEXT,
    is_sensitive BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    version_lock INT NOT NULL DEFAULT 0,
    CONSTRAINT uq_system_configuration_key UNIQUE (tenant_id, config_key)
    );

CREATE INDEX IF NOT EXISTS idx_audit_log_tenant_created_at
    ON audit_log (tenant_id, created_at);

CREATE INDEX IF NOT EXISTS idx_audit_log_correlation_id
    ON audit_log (correlation_id);

CREATE INDEX IF NOT EXISTS idx_domain_event_outbox_status
    ON domain_event_outbox (status);

CREATE INDEX IF NOT EXISTS idx_domain_event_outbox_tenant_created_at
    ON domain_event_outbox (tenant_id, created_at);