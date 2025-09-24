package com.lc.Service;

public interface RedisService {
	void set(String key, String value);
	String get(String key);
	void del(String key);
	void delByPattern(String pattern);
}


