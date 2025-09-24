package com.lc.Controller;

import com.lc.Entity.Schedule;
import com.lc.Service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

	@Resource
	private ScheduleService scheduleService;

    // B. 查询出行计划信息：支持指定出行方式或全部
    @GetMapping
    public List<Schedule> list(@RequestParam(value = "tripModeId", required = false) Long tripModeId) {
        if (tripModeId == null) {
            return scheduleService.listAll();
        }
        return scheduleService.listByTripModeId(tripModeId);
    }

	// 根据ID查询单条
	@GetMapping("/{id}")
	public Schedule findById(@PathVariable("id") Long id) {
		return scheduleService.findById(id);
	}

	// C. 添加出行计划信息
	@PostMapping
	public Schedule create(@RequestBody Schedule schedule) {
		if (schedule.getStatus() == null) {
			// 默认未出行状态为 0
			schedule.setStatus(0);
		}
		return scheduleService.create(schedule);
	}

	// D. 修改出行计划信息
	@PutMapping("/{id}")
	public int update(@PathVariable("id") Long id, @RequestBody Schedule schedule) {
		schedule.setId(id);
		return scheduleService.update(schedule);
	}

	// 删除出行计划信息
	@DeleteMapping("/{id}")
	public int delete(@PathVariable("id") Long id) {
		return scheduleService.deleteById(id);
	}

	// 修改出行的状态 改为取消状态
	@PutMapping("/{id}/cancel")
	public int cancel(@PathVariable("id") Long id) {
		return scheduleService.cancelById(id);
	}
}


