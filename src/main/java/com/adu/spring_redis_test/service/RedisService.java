package com.adu.spring_redis_test.service;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisService {
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	public String get(final String key) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] keyBytes = redisTemplate.getStringSerializer()
						.serialize(key);
				if (connection.exists(keyBytes)) {
					byte[] valueBytes = connection.get(keyBytes);
					String value = redisTemplate.getStringSerializer()
							.deserialize(valueBytes);
					return value;
				}
				return null;
			}
		});
	}

	public void set(final String key, final String value) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(
						redisTemplate.getStringSerializer().serialize(key),
						redisTemplate.getStringSerializer().serialize(value));
				return null;
			}
		});
	}

	public long del(final String key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				Long ret = connection.del(redisTemplate.getStringSerializer()
						.serialize(key));
				return ret;
			}
		});
	}

	public void setRedisTemplate(
			RedisTemplate<Serializable, Serializable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}
