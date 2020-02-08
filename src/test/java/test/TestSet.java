package test;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class TestSet {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Test
	public void testSet(){
		redisTemplate.boundSetOps("nameSet").add("叶城");
		redisTemplate.boundSetOps("nameSet").add("姜堰");
		redisTemplate.boundSetOps("nameSet").add("林语");
		redisTemplate.boundSetOps("nameSet").add("傅玉书");
	}
	
	@Test
	public void testGet(){
		Set members = redisTemplate.boundSetOps("nameSet").members();
		System.out.println(members);
	}
	
	@Test
	public void removetest(){
		redisTemplate.boundSetOps("nameSet").remove("叶城");
	}
	
	@Test
	public void delteTest(){
		redisTemplate.delete("nameSet");
	}
	
}
