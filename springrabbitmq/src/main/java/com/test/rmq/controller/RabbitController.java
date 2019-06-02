package com.test.rmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("org/rabbitmq")
public class RabbitController {
	
	@Autowired
	RabbitTemplate rt;
	
	@Value("${org.rabbit.exchange}")
	String exchangeName;
	
	@Value("${org.rabbit.route.key}")
	String routeKeyName;
	
	
	@PostMapping(value="/publish",consumes = "application/json",produces="application/json")
	@ResponseBody
	public ResponseEntity<String> publishMsg(@RequestBody String msgbody){
		String response = "Messsage Send";
		rt.convertAndSend(exchangeName, routeKeyName, msgbody);		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	

}
