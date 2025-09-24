package com.lc.Service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lc.Entity.Schedule;
import com.lc.Mapper.ScheduleMapper;
import com.lc.Service.ScheduleService;
import com.lc.Service.RedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Resource
	private ScheduleMapper scheduleMapper;

	@Resource
	private RedisService redisService;

    @Override
    public List<Schedule> listByTripModeId(Long tripModeId) {
        String key = "schedules:mode:" + tripModeId;
        try {
            String cached = redisService.get(key);
            if (cached != null && !cached.isEmpty()) {
                return MAPPER.readValue(cached, new TypeReference<java.util.List<Schedule>>(){});
            }
        } catch (Exception ignore) {}

        List<Schedule> list = scheduleMapper.findByTripModeId(tripModeId);
        try {
            redisService.set(key, MAPPER.writeValueAsString(list));
        } catch (Exception ignore) {}
        return list;
    }

    @Override
    public List<Schedule> listAll() {
        String key = "schedules:all";
        try {
            String cached = redisService.get(key);
            if (cached != null && !cached.isEmpty()) {
                return MAPPER.readValue(cached, new TypeReference<java.util.List<Schedule>>(){});
            }
        } catch (Exception ignore) {}

        List<Schedule> list = scheduleMapper.findAll();
        try {
            redisService.set(key, MAPPER.writeValueAsString(list));
        } catch (Exception ignore) {}
        return list;
    }

	@Override
    public Schedule findById(Long id) {
        return scheduleMapper.findById(id);
    }

	@Override
    public Schedule create(Schedule schedule) {
        scheduleMapper.insert(schedule);
        // invalidate caches
        redisService.del("schedules:all");
        if (schedule.getTrip_mode_id() != null) {
            redisService.del("schedules:mode:" + schedule.getTrip_mode_id());
        }
        return schedule;
    }

	@Override
    public int update(Schedule schedule) {
        int n = scheduleMapper.update(schedule);
        redisService.del("schedules:all");
        redisService.delByPattern("schedules:mode:*");
        return n;
    }

	@Override
    public int deleteById(Long id) {
        int n = scheduleMapper.deleteById(id);
        redisService.del("schedules:all");
        redisService.delByPattern("schedules:mode:*");
        return n;
    }

	@Override
    public int cancelById(Long id) {
        int n = scheduleMapper.cancelById(id, 1);
        redisService.del("schedules:all");
        redisService.delByPattern("schedules:mode:*");
        return n;
    }

    // Jackson mapper that supports Java 8 date/time types
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
}


