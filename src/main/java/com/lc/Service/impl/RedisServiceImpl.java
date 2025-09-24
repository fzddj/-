package com.lc.Service.impl;

import com.lc.Service.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	@Override
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public void del(String key) {
		stringRedisTemplate.delete(key);
	}

	@Override
	public void delByPattern(String pattern) {
		// 使用 scan 避免 keys 阻塞
        stringRedisTemplate.execute((org.springframework.data.redis.core.RedisCallback<Object>) connection -> {
			try (org.springframework.data.redis.core.Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match(pattern).count(1000).build())) {
				while (cursor.hasNext()) {
					byte[] key = cursor.next();
					connection.keyCommands().del(key);
				}
			}
			return null;
        });
	}
}


