package com.lc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/", "/index", "/index.html"})
	public String index() {
		return "index";
	}

    @GetMapping({"/add", "/add.html"})
	public String add() {
		return "add";
	}

    @GetMapping({"/edit", "/edit.html"})
	public String edit() {
		return "edit";
	}
}


