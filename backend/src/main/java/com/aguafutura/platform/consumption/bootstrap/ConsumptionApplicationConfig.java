package com.aguafutura.platform.consumption.bootstrap;

import com.aguafutura.platform.consumption.application.ListConsumptionsUseCase;
import com.aguafutura.platform.consumption.application.RegisterConsumptionUseCase;
import com.aguafutura.platform.consumption.application.port.ConsumptionRepositoryPort;
import com.aguafutura.platform.consumption.infrastructure.persistence.jpa.ConsumptionJpaRepository;
import com.aguafutura.platform.consumption.infrastructure.persistence.jpa.ConsumptionPersistenceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumptionApplicationConfig {

    @Bean
    public ConsumptionRepositoryPort consumptionRepositoryPort(ConsumptionJpaRepository repository) {
        return new ConsumptionPersistenceAdapter(repository);
    }

    @Bean
    public RegisterConsumptionUseCase registerConsumptionUseCase(ConsumptionRepositoryPort port) {
        return new RegisterConsumptionUseCase(port);
    }

    @Bean
    public ListConsumptionsUseCase listConsumptionsUseCase(ConsumptionRepositoryPort port) {
        return new ListConsumptionsUseCase(port);
    }
}
