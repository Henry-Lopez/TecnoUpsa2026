package com.aguafutura.platform.ai.bootstrap;

import com.aguafutura.platform.ai.application.DetectAnomalyUseCase;
import com.aguafutura.platform.consumption.application.port.ConsumptionRepositoryPort;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiApplicationConfig {

    @Bean
    public DetectAnomalyUseCase detectAnomalyUseCase(ChatModel chatModel, ConsumptionRepositoryPort consumptionRepositoryPort) {
        return new DetectAnomalyUseCase(chatModel, consumptionRepositoryPort);
    }
}
