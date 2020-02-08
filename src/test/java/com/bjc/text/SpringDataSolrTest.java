package com.bjc.text;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.response.UpdateResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pinyougou.pojo.TbItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-solr.xml")
public class SpringDataSolrTest {

	@Autowired
	private SolrTemplate solrTemplate;
	
	@Test
	public void testAdd(){
		TbItem item=new TbItem();
		item.setId(1L);
		item.setBrand("华为123");
		item.setCategory("手机");
		item.setGoodsId(1L);
		item.setSeller("华为2号专卖店");
		item.setTitle("华为Mate9");
		item.setPrice(new BigDecimal(2000));

		solrTemplate.saveBean(item);
		solrTemplate.commit();
		
	}
	
	@Test
	public  void testFindById(){
		TbItem byId = solrTemplate.getById(1L, TbItem.class);
		System.out.println(byId);
	}
	
	@Test
	public void testDeleteById(){
		solrTemplate.deleteById("1");
		solrTemplate.commit();
	}
	
	@Test
	public void testAddList(){
		List<TbItem> list = new ArrayList<TbItem>();
		for(int i = 0 ; i < 100 ; i ++){
			TbItem item=new TbItem();
			item.setId(i+1L);
			item.setBrand("华为_" + i);
			item.setCategory("手机_" + i);
			item.setGoodsId(i+1L);
			item.setSeller("华为"+i+1+"号专卖店");
			item.setTitle("华为Mate"+i);
			item.setPrice(new BigDecimal(2000+1));
			list.add(item);
		}
		solrTemplate.saveBeans(list);
		solrTemplate.commit();
		
	}
	
	@Test
	public void testFindByPage(){
		// 构建一个查询对象  参数是查询表达式
		Query query = new SimpleQuery("*:*");
		
		/* 设置分页参数 */
		// 1. 设置开始位置  相当于界面中的start
		query.setOffset(0);
		// 2. 每页记录数   相当于界面中的rows
		query.setRows(20);
		
		// 默认查询前10条数据 
		ScoredPage<TbItem> queryForPage = solrTemplate.queryForPage(query, TbItem.class);
		/*for(TbItem t : queryForPage){
			System.out.println(t);
		}*/
		
		List<TbItem> content = queryForPage.getContent(); // 每页的结果
		for(TbItem t : content){
			System.out.println(t);
		}
		
		System.out.println("总记录数: " + queryForPage.getTotalElements());
		System.out.println("每页记录数: " + queryForPage.getSize());
		System.out.println("总页数: " + queryForPage.getTotalPages());
		
	}
	
	@Test
	public void testCriteria(){
		Query query = new SimpleQuery("*:*");
		Criteria criteria = new Criteria("item_category");
		criteria.contains("手机_1");
		query.addCriteria(criteria );
		
		/* 设置分页参数 */
		// 1. 设置开始位置  相当于界面中的start
		query.setOffset(0);
		// 2. 每页记录数   相当于界面中的rows
		query.setRows(20);
		
		// 默认查询前10条数据 
		ScoredPage<TbItem> queryForPage = solrTemplate.queryForPage(query, TbItem.class);
		
		List<TbItem> content = queryForPage.getContent(); // 每页的结果
		System.out.println("总记录数: " + queryForPage.getTotalElements());
		System.out.println("每页记录数: " + queryForPage.getSize());
		System.out.println("总页数: " + queryForPage.getTotalPages());
	}
	
	@Test
	public void deleteAllTest(){
		Query query = new SimpleQuery();
		Criteria criteria = new Criteria("item_category");
		criteria.contains("手机_1");
		query.addCriteria(criteria );
		solrTemplate.delete(query);
		
		solrTemplate.commit();
	}

}
