package com.aguafutura.platform.ai.domain;

public record AnomalyReport(
        boolean isAnomaly,
        String analysis,
        String recommendation
) {}
