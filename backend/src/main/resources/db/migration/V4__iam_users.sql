CREATE TABLE IF NOT EXISTS iam_users (
                                         id UUID PRIMARY KEY,
                                         tenant_id UUID NOT NULL REFERENCES tenant(id),
    full_name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    version_lock INT NOT NULL DEFAULT 0,

    CONSTRAINT uk_iam_user_tenant_email UNIQUE (tenant_id, email)
    );

CREATE INDEX IF NOT EXISTS idx_iam_users_tenant_id
    ON iam_users (tenant_id);

CREATE INDEX IF NOT EXISTS idx_iam_users_tenant_email
    ON iam_users (tenant_id, email);