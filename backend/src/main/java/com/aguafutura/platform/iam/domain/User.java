package com.aguafutura.platform.iam.domain;

import java.util.UUID;

public class User {

    private final UUID id;
    private final UUID tenantId;
    private final String fullName;
    private final String email;
    private final String passwordHash;
    private final UserRole role;
    private final boolean enabled;

    public User(
            UUID id,
            UUID tenantId,
            String fullName,
            String email,
            String passwordHash,
            UserRole role,
            boolean enabled
    ) {
        if (id == null) throw new IllegalArgumentException("User id is required");
        if (tenantId == null) throw new IllegalArgumentException("Tenant id is required");
        if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException("Full name is required");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email is required");
        if (passwordHash == null || passwordHash.isBlank()) throw new IllegalArgumentException("Password hash is required");
        if (role == null) throw new IllegalArgumentException("Role is required");

        this.id = id;
        this.tenantId = tenantId;
        this.fullName = fullName;
        this.email = email.toLowerCase().trim();
        this.passwordHash = passwordHash;
        this.role = role;
        this.enabled = enabled;
    }

    public static User create(UUID tenantId, String fullName, String email, String passwordHash, UserRole role) {
        return new User(
                UUID.randomUUID(),
                tenantId,
                fullName,
                email,
                passwordHash,
                role,
                true
        );
    }

    public UUID getId() {
        return id;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean isEnabled() {
        return enabled;
    }
}