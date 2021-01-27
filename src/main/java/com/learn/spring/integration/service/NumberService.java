package com.learn.spring.integration.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface NumberService {

	@Gateway(requestChannel = "numberFlow.input")
	void send(int[] numbers);
}
