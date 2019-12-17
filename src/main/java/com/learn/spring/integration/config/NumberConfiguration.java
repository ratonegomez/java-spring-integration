package com.learn.spring.integration.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Configuration
@EnableIntegration
@IntegrationComponentScan
@Slf4j
public class NumberConfiguration {

    @Bean
    public IntegrationFlow numberIntegrationFlow(){
        return IntegrationFlows.from("producer")
                .channel("message.channel")
                .get();
    }

    @Bean
    @ServiceActivator(inputChannel = "message.channel")
    public MessageHandler consumer(){
        return m->log.info("Message received {}",m.getPayload());
    }
}
