package com.enjoy.trip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeController {
	@GetMapping("list")
	public String list(Model model) {
		return "/notice/list";
	}
	@GetMapping("view")
	public String view(Model model, @RequestParam("no") int no) {
		return "/notice/view";
	}
}
