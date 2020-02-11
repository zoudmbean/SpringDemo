package cn.bjc;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-jms-topic-consumer.xml")
public class TopicConsumertest {
	
	@Test
	public void testQueue() throws IOException{
		System.in.read();
	}

}
