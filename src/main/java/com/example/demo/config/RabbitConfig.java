package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qsky on 2018/8/7
 */
@Configuration
public class RabbitConfig {

	final static String message = "test.message";
	final static String messages = "test.messages";

	@Bean
	public Queue queueMessage() {
		return new Queue(RabbitConfig.message);
	}

	@Bean
	public Queue queueMessages() {
		return new Queue(RabbitConfig.messages);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("exchange");
	}

	@Bean
	Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with("test.message");
	}

	@Bean
	Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessages).to(exchange).with("test.#");
	}
}
