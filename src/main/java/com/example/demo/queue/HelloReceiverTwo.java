package com.example.demo.queue;

import com.example.demo.data.UserData;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author qsky on 2018/8/13
 */
@Component
public class HelloReceiverTwo {

	@RabbitHandler
	@RabbitListener(queues = "test.messages")
	public void process(UserData user) {
		System.out.println("Receiver 2 : " + user.toString());
	}
}
