package com.enjoy.trip.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enjoy.trip.dto.User;
import com.enjoy.trip.service.MyPageService;

@RestController
@RequestMapping("/api/myInfo")
public class MyPageRestController {
	private final MyPageService service;
	
	public MyPageRestController(MyPageService service) {
		this.service = service;
	}
	
	@GetMapping("{userNo}")
	public User myPage(@PathVariable("userNo") int userNo) {
		return service.getUser(userNo);
	}
	@PutMapping("{userNo}")
	public int updateInfo(@RequestBody User param, @PathVariable("userNo") int userNo) {
		param.setNo(userNo);
		return service.updateUser(param);
	}
	
}
