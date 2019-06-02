package com.test.rmq.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

public class RabbitFactoryConfig {

	@Bean
	public RabbitConnectionFactoryBean rabbitConnFactory() {
		RabbitConnectionFactoryBean rf = new RabbitConnectionFactoryBean();
		return rf;
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory cf) {
		RabbitTemplate rt = new RabbitTemplate(cf);
		rt.setMessageConverter(jsonMessageConverter());
		return rt;
	}

}
