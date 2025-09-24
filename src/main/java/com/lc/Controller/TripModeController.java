package com.lc.Controller;

import com.lc.Entity.TripMode;
import com.lc.Service.TripModeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/trip-modes")
public class TripModeController {

	@Resource
	private TripModeService tripModeService;

	// A. 查询所有出行方式
    @GetMapping
    public List<TripMode> listAll() {
        return tripModeService.listAll();
    }
}


