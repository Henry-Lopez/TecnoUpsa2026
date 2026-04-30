package com.aguafutura.platform.core.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoreTestController {

    @GetMapping("/api/v1/core/test-error")
    public String testError() {
        throw new IllegalArgumentException("Core Platform test error");
    }
}