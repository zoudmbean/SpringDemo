package test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class TestHash {

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Test
	public void testSet(){
		redisTemplate.boundHashOps("namHash").put("name", "张三");
		redisTemplate.boundHashOps("namHash").put("age", 22);
		redisTemplate.boundHashOps("namHash").put("birth", new Date());
	}
	
	@Test
	public void testGet(){
		Date date = (Date) redisTemplate.boundHashOps("namHash").get("birth");
		System.out.println(date);
	}
	
	@Test
	public void testGet2(){
		List values = redisTemplate.boundHashOps("namHash").values();
		System.out.println(values);
	}
	
	@Test
	public void testGet1(){
		Set keys = redisTemplate.boundHashOps("namHash").keys();
		Iterator iterator = keys.iterator();
		while (iterator.hasNext()) {
			String next = (String) iterator.next();
			Object object = redisTemplate.boundHashOps("namHash").get(next);
			System.out.println(object);
		}
	}
	
	@Test
	public void testRemove(){
		redisTemplate.boundHashOps("namHash").delete("name");
	}
	
	@Test
	public void testRemove1(){
		redisTemplate.delete("namHash");
	}
	
}
