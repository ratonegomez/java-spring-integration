package com.learn.spring.integration;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.spring.integration.service.NumberService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberConfigurationTest {

    @Autowired
    private NumberService numberService;

    @Test
    public void sendSimpleMessage(){
        numberService.send(IntStream.rangeClosed(1,10).toArray());        
    }

}
