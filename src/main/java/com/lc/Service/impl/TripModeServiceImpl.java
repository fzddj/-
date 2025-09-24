package com.lc.Service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.Entity.TripMode;
import com.lc.Mapper.TripModeMapper;
import com.lc.Service.RedisService;
import com.lc.Service.TripModeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class TripModeServiceImpl implements TripModeService {

	@Resource
	private TripModeMapper tripModeMapper;

	@Resource
	private RedisService redisService;

	@Override
	public List<TripMode> listAll() {
		// 先读缓存
		try {
			String cached = redisService.get("trip:modes");
			if (cached != null && !cached.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.readValue(cached, new TypeReference<List<TripMode>>(){});
			}
		} catch (Exception ignore) {}

		// 缓存没有则查库并回填
		List<TripMode> list = tripModeMapper.findAll();
		try {
			ObjectMapper mapper = new ObjectMapper();
			redisService.set("trip:modes", mapper.writeValueAsString(list));
		} catch (Exception ignore) {}
		return list == null ? Collections.emptyList() : list;
	}
}


