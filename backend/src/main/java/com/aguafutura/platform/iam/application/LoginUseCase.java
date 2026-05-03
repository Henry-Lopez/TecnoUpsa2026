package com.aguafutura.platform.iam.application;

import com.aguafutura.platform.iam.application.port.JwtTokenPort;
import com.aguafutura.platform.iam.application.port.PasswordHasherPort;
import com.aguafutura.platform.iam.application.port.UserRepositoryPort;
import com.aguafutura.platform.iam.domain.User;

import java.util.UUID;

public class LoginUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordHasherPort passwordHasher;
    private final JwtTokenPort jwtTokenPort;

    public LoginUseCase(
            UserRepositoryPort userRepository,
            PasswordHasherPort passwordHasher,
            JwtTokenPort jwtTokenPort
    ) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.jwtTokenPort = jwtTokenPort;
    }

    public String execute(UUID tenantId, String email, String rawPassword) {
        User user = userRepository.findByTenantIdAndEmail(tenantId, email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!user.isEnabled()) {
            throw new IllegalArgumentException("User is disabled");
        }

        if (!passwordHasher.matches(rawPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return jwtTokenPort.generateAccessToken(user);
    }
}