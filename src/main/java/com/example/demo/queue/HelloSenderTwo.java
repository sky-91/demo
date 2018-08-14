package com.example.demo.queue;

import com.example.demo.data.UserDetailData;
import javax.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * @author qsky on 2018/8/13
 */
@Component
public class HelloSenderTwo {


	@Resource
	private AmqpTemplate rabbitTemplate;

	public void send(UserDetailData user) {
		rabbitTemplate.convertAndSend("mqObject", user);
	}
}
