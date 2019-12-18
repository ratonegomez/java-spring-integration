package com.learn.spring.integration.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageHandler;

import java.util.function.Predicate;

@Configuration
@EnableIntegration
@Slf4j
public class NumberConfiguration {

    private final Predicate<Integer> isEven = s -> s % 2 == 0;

    @Bean
    public IntegrationFlow numberIntegrationFlow() {

        return IntegrationFlows.from("producer")
                .split()
                .routeToRecipients(r ->
                        r.recipientFlow(isEven::test, f -> f.channel("evenChannel"))
                                .recipientFlow(isEven.negate()::test, f -> f.channel("oddChannel"))
                )
                .get();
    }

    @Bean
    @ServiceActivator(inputChannel = "evenChannel")
    public MessageHandler evenChannelHandler() {
        return m -> log.info("Even numbers: {}", m.getPayload());
    }



    @Bean
    @ServiceActivator(inputChannel = "oddChannel")
    public MessageHandler oddChannelhandler() {
        return m -> log.info("Odd numbers {}", m.getPayload());
    }

}
