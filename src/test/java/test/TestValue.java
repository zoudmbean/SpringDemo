package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class TestValue {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Test
	public void testSetValue(){
		BoundValueOperations boundValueOps = redisTemplate.boundValueOps("name");
		boundValueOps.set("张三");
	}
	
	@Test
	public void getValue(){
		String name = (String) redisTemplate.boundValueOps("name").get();
		System.out.println(name);
	}
	
	@Test
	public void deleValue(){
		redisTemplate.delete("name");
	}
	
}
