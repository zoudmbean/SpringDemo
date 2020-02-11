package com.bjc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bjc.demo.QueueProducer;
import com.bjc.demo.TopicProducer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-jms-producer.xml")
public class QueueProducerTest {
	
	@Autowired
	private QueueProducer queueProducer;
	
	@Autowired
	private TopicProducer topicProducer;

	@Test
	public void sendTest(){
		queueProducer.sendTextMsg("你好 SpringJMS！");
	}
	
	@Test
	public void sendTest2(){
		topicProducer.sendmsg("你好 SpringJMS Topic！");
	}
	
}
