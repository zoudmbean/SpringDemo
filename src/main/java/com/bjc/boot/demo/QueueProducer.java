package com.bjc.boot.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class QueueProducer {

	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@RequestMapping("/send")
	public void sendMesg(String text){
		jmsMessagingTemplate.convertAndSend("bjc", text);
	}
	
	@RequestMapping("/sendMap")
	public void sendMapMesg(){
		Map map = new HashMap();
		map.put("name", "张三");
		map.put("age", 22);
		map.put("birth", "2019-01-02");
		jmsMessagingTemplate.convertAndSend("bjcMap", map);
	}
	
}
