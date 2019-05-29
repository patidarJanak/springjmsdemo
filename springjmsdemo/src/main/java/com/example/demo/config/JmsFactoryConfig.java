package com.example.demo.config;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.springframework.boot.jms.*;
@Configuration
public class JmsFactoryConfig {
	
	@Value("${test.mq.host}")
	private String hostName;
	
	@Value("${test.mq.port}")
	private Integer protNum;
	
	@Value("${test.mq.queue.manager}")
	private String managerName;
	
	@Value("${test.mq.channel}")
	private String channelName;
	
	@Value("${test.mq.queue}")
	private String queueName;
	
	@Value("${test.mq.receive.timeout}")
	private Integer timeOut;

	@Bean
	public MQQueueConnectionFactory maQueueConnectionFactory() {
		
		MQQueueConnectionFactory maQueueConnectionFactory = new MQQueueConnectionFactory();
		try {
			maQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
			maQueueConnectionFactory.setCCSID(1208);
			maQueueConnectionFactory.setChannel(channelName);
			maQueueConnectionFactory.setPort(protNum);
			maQueueConnectionFactory.setQueueManager(managerName);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maQueueConnectionFactory;
	}
	
	@Bean
	public JmsTemplate getJmsTemplate() {
		try {
			return new JmsTemplate(maQueueConnectionFactory());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
