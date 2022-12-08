package com.encore.petandbe.infrastructure.oauth;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisManager {

	private final RedisTemplate<String, String> redisTemplate;
	private final ValueOperations<String, String> values;

	public RedisManager(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
		values = redisTemplate.opsForValue();
	}

	public void setValues(String key, String value) {
		values.set(key, value);
	}

	public void setValuesWithDuration(String key, String value, Duration duration) {
		values.set(key, value, duration);
	}

	public String getValues(String key) {
		return values.get(key);
	}

	public void deleteValues(String key) {
		redisTemplate.delete(key);
	}
}