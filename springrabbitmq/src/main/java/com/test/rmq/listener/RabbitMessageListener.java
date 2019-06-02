package com.test.rmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMessageListener {

	@RabbitListener(queues = "${org.rabbit.queue}")
	public void queueListener(final Message msg) {
		System.out.println("Rabbit Message body " + msg);

	}

}
