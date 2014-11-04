package com.adu.spring_redis_test.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/adu/spring_redis_test/service/service.xml")
public class RedisServiceTest {
	@Autowired
	private RedisService redisService;
	private Log logger = LogFactory.getLog(this.getClass());

	@Test
	public void get() {
		String key = "YUNJIEDU:TEST:test";
		String ret = redisService.get(key);
		logger.debug("ret=" + ret);
	}

	@Test
	public void set() {
		String key = "YUNJIEDU:TEST:test";
		String value = "hello,world";
		redisService.set(key, value);
		logger.debug("end~");
	}

	@Test
	public void del() {
		String key = "YUNJIEDU:TEST:test";
		long ret = redisService.del(key);
		logger.debug("ret=" + ret);
	}
}
