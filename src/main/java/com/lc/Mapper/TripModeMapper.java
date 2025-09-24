package com.lc.Mapper;

import com.lc.Entity.TripMode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TripModeMapper {
	List<TripMode> findAll();
}


