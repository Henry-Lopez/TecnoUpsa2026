package com.aguafutura.platform.iam.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeController {

    @GetMapping("/api/v1/auth/me")
    public ResponseEntity<MeResponse> me(Authentication authentication) {
        String userId = authentication.getName();
        String tenantId = authentication.getDetails().toString();

        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok(
                new MeResponse(
                        userId,
                        tenantId,
                        roles
                )
        );
    }
}