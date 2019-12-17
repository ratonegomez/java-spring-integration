package com.learn.spring.integration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberConfigurationTest {

    @Autowired
    private MessageChannel producer;

    @Test
    public void sendSimpleMessage(){
        final Message<Integer> message= MessageBuilder.withPayload(2).build();
        producer.send(message); 
    }

}
