package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class TestList {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Test
	public void testset(){
		redisTemplate.boundListOps("namelist").rightPush("李四1");
		redisTemplate.boundListOps("namelist").rightPush("李四2");
		redisTemplate.boundListOps("namelist").rightPush("李四3");
		redisTemplate.boundListOps("namelist").leftPush("张三");
		redisTemplate.boundListOps("namelist").leftPush("张三1");
		redisTemplate.boundListOps("namelist").leftPush("张三2");
	}
	
	@Test
	public void testGet(){
		List range = redisTemplate.boundListOps("namelist").range(0, 100);
		System.out.println(range);
	}
	
	@Test
	public void searchById(){
		String name = (String) redisTemplate.boundListOps("namelist").index(1);
		System.out.println(name);
	}
	
	@Test
	public void removetest(){
		Long remove = redisTemplate.boundListOps("namelist").remove(2, "李四1");
		System.out.println(remove);
	}
	
	// [张三2, 张三1, 张三, 李四2, 李四3]
	@Test
	public void test01(){
		String name = (String) redisTemplate.boundListOps("namelist").leftPop();
		System.out.println(name);
	}
	
	// [张三1, 张三, 李四2, 李四3]
	@Test
	public void test02(){
		String name = (String) redisTemplate.boundListOps("namelist").rightPop();
		System.out.println(name);
	}
}
