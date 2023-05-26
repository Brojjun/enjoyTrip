package com.enjoy.trip.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enjoy.trip.dto.ShareAttraction;
import com.enjoy.trip.service.ShareAttractionService;

@RestController
@RequestMapping("/api/shareattraction")
public class ShareAttractionRestController {
	private ShareAttractionService shareAttractionService;
	public ShareAttractionRestController(ShareAttractionService shareAttractionService) {
		this.shareAttractionService = shareAttractionService;
	}
	
	@GetMapping("/list")
	public List<ShareAttraction> select() throws Exception {
		return shareAttractionService.selectShareAttraction();
	}
	
	//share_attration 추가
	@PostMapping("/write")
	public void writeAttraction(@RequestBody ShareAttraction param) throws Exception {
		//int no = 1; //user 객체의 no
		//param.setShareNo(no);
		shareAttractionService.writeShareAttraction(param);
	}
	
	@GetMapping("/delete/{shareNo}")
	public void deleteAttraction(@PathVariable int shareNo) throws Exception {
		shareAttractionService.deleteShareAttraction(shareNo);
	}
}
