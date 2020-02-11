package com.bjc.demo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener{

	// 这个message就是监听到的message
	public void onMessage(Message message) {
		TextMessage m = (TextMessage)message;
		try {
			System.out.println("接收到的消息：" + m.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
