package com.bjc.boot.demo;

import java.util.Map;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {
	
	@JmsListener(destination = "bjc")
	public void receive(String text){
		System.out.println("接收到的消息是：" + text);
	}
	
	@JmsListener(destination = "bjcMap")
	public void receiveMap(Map map){
		System.out.println("接收到的消息是：" + map);
	}
}
