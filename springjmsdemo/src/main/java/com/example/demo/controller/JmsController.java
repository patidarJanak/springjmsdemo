package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("jms")
public class JmsController {
	
	@Value("${test.mq.queue}")
	String queueName;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@GetMapping("/publish")
	public boolean publish() {
		try {
			String message ="{  \r\n" + 
					"   \"testmsg\":\"jsmmsg\"\r\n" + 
					"}";
			jmsTemplate.convertAndSend(message);
		} catch (JmsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}
	
	@GetMapping("/getmsg")
	@ResponseBody
	public Object getMsg() {
		Object obj= jmsTemplate.receiveAndConvert(queueName);
		System.out.println("Message Data:"+obj.toString());
		return obj;
	}
	

}
