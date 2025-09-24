package com.lc.Mapper;

import com.lc.Entity.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScheduleMapper {
	List<Schedule> findAll();
	List<Schedule> findByTripModeId(@Param("tripModeId") Long tripModeId);
	Schedule findById(@Param("id") Long id);
	int insert(Schedule schedule);
	int update(Schedule schedule);
	int deleteById(@Param("id") Long id);
	int cancelById(@Param("id") Long id, @Param("cancelStatus") Integer cancelStatus);
}


