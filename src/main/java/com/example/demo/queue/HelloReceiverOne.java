package com.example.demo.queue;

import com.example.demo.data.UserData;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author qsky on 2018/8/7
 */
@Component
public class HelloReceiverOne {

	@RabbitHandler
	@RabbitListener(queues = "test.message")
	public void process(UserData user) {
		System.out.println("Receiver 1 : " + user.toString());
	}

	@RabbitHandler
	@RabbitListener(queues = "fanout.A")
	public void process1(String msg) {
		System.out.println("Receiver A : " + msg);
	}

	@RabbitHandler
	@RabbitListener(queues = "fanout.B")
	public void process2(String msg) {
		System.out.println("Receiver B : " + msg);
	}

	@RabbitHandler
	@RabbitListener(queues = "fanout.C")
	public void process3(String msg) {
		System.out.println("Receiver C : " + msg);
	}
}
