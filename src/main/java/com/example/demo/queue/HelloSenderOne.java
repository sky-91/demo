package com.example.demo.queue;

import com.example.demo.data.UserData;
import com.example.demo.data.UserDetailData;
import javax.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * @author qsky on 2018/8/7
 */
@Component
public class HelloSenderOne {

	@Resource
	private AmqpTemplate rabbitTemplate;

	public void send(UserData user) {
		rabbitTemplate.convertAndSend("exchange", "test.hello", user);
	}

	public void send1(UserDetailData user) {
		rabbitTemplate.convertAndSend("exchange", "test.message", user);
	}

	public void send2(UserData user) {
		rabbitTemplate.convertAndSend("exchange", "test.messages", user);
	}

	public void send3(String msg) {
		rabbitTemplate.convertAndSend("fanoutExchange", "abc", msg);
	}
}
