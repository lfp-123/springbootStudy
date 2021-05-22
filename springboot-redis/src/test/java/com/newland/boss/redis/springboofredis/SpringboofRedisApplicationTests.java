package com.newland.boss.redis.springboofredis;

import com.google.gson.Gson;
import com.newland.boss.redis.springboofredis.pojo.Person;
import com.newland.boss.redis.springboofredis.service.impl.IRedisServiceImpl;
import net.bytebuddy.description.method.MethodDescription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
class SpringboofRedisApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	IRedisServiceImpl impl;
	@Test
	public void test() {
		stringRedisTemplate.opsForValue().set("key", "value");
		String string = stringRedisTemplate.opsForValue().get("key");
		System.out.println(string);
		String name = stringRedisTemplate.opsForValue().get("name");
		System.out.println(name);
		Person person = new Person();
		person.setId(123);
		person.setName("test");
		redisTemplate.opsForValue().set("key-0", person);
		Person person1 = (Person) redisTemplate.opsForValue().get("key-0");
		System.out.println(person1);
	}
	@Test
	public void testDB(){
		String sql ="insert into redis_test_data (key,column_2,column_3,column_4,column_5,column_6," +
				"column_7,column_8,column_9,column_10,column_11,column_12,column_13,column_14,column_15) values (" +
				"SEQ_REDIS_TEST_DATA.nextval,?,99992,99993,99994,99995,99996,99997,99998,99999,10000,10001,10002,10003,10004)";
			List<Object[]> batchArgs = new ArrayList<Object[]>();
		long l = System.currentTimeMillis()/1000;
		for (int i = 0; i < 100000; i++) {
			batchArgs.add(new Object[] {i});
		}
		jdbcTemplate.batchUpdate(sql, batchArgs);
		long o= System.currentTimeMillis()/1000;
		System.out.println(l);
		System.out.println(o);
		System.out.println(l-o);
	}
	@Test
	public void testA() {
		ArrayList<ConcurrentHashMap<String, ArrayList<Object>>> listMaps = new ArrayList<>();
		String sql = "select * from redis_test_data where key =10006001";
		List rows = jdbcTemplate.queryForList(sql);
		Iterator it = rows.iterator();
		while (it.hasNext()) {
			ArrayList<Object> list = new ArrayList<>();
			ConcurrentHashMap<String, ArrayList<Object>> map = new ConcurrentHashMap<>();
			Map data = (Map) it.next();
			BigDecimal key = (BigDecimal) data.get("key");
			System.out.println(key.toString());
		}
	}

	@Test
	public void TestGet(){

		long startTime = System.currentTimeMillis();
		Object value = impl.getValue("10000562");
		long endTime = System.currentTimeMillis();
		long seconds = (endTime - startTime);
		System.out.println(startTime);
		System.out.println(endTime);
		System.out.println(value.toString());
		System.out.println(seconds/1000+ " seconds.");
	}


}


