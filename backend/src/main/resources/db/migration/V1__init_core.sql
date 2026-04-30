CREATE TABLE IF NOT EXISTS app_health_marker (
                                                 id UUID PRIMARY KEY,
                                                 name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
    );