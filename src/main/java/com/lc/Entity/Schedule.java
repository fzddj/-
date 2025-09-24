package com.lc.Entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Schedule implements Serializable {
	private Long id;
	private String user_name;
	private String duty;
	private Long trip_mode_id;
	private String trip_mode_name;
	private Integer status;
	private LocalDate depart_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public Long getTrip_mode_id() {
		return trip_mode_id;
	}

	public void setTrip_mode_id(Long trip_mode_id) {
		this.trip_mode_id = trip_mode_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDate getDepart_time() {
		return depart_time;
	}

	public void setDepart_time(LocalDate depart_time) {
		this.depart_time = depart_time;
	}

	public String getTrip_mode_name() {
		return trip_mode_name;
	}

	public void setTrip_mode_name(String trip_mode_name) {
		this.trip_mode_name = trip_mode_name;
	}
}


