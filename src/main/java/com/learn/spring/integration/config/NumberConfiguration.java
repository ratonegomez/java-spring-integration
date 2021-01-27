package com.learn.spring.integration.config;

import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableIntegration
@Slf4j
public class NumberConfiguration {

    private final Predicate<Integer> isEven = s -> s % 2 == 0;

    @Bean
    public IntegrationFlow numberFlow() {
        return f->f
                .split()
                .routeToRecipients(r ->
                        r.recipientFlow(isEven::test, sf -> sf.channel("evenChannel"))
                                .recipientFlow(isEven.negate()::test, sf -> sf.channel("oddChannel"))
                );
    }

    @ServiceActivator(inputChannel = "evenChannel")
    public void evenChannelHandler(int p) {
        log.info("Even numbers: {}", p);
    }

    @ServiceActivator(inputChannel = "oddChannel")
    public void oddChannelhandler(int p) {
        log.info("Odd numbers {}", p);
    }

}
