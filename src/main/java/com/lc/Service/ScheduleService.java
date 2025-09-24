package com.lc.Service;

import com.lc.Entity.Schedule;

import java.util.List;

public interface ScheduleService {
	List<Schedule> listByTripModeId(Long tripModeId);
	List<Schedule> listAll();
	Schedule findById(Long id);
	Schedule create(Schedule schedule);
	int update(Schedule schedule);
	int deleteById(Long id);
	int cancelById(Long id);
}


