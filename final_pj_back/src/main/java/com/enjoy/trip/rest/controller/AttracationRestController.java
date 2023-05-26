package com.enjoy.trip.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enjoy.trip.paging.Page;
import com.enjoy.trip.service.AttractionService;

@RestController
@RequestMapping("/api/attraction")
@CrossOrigin("*")
public class AttracationRestController {
	private final AttractionService service;
	
	
	public AttracationRestController(AttractionService service) {
		this.service = service;
	}
	
	@GetMapping("list")
	public Map<String, Object> selectAttractionList(@RequestParam HashMap<String, Object> param){
		//페이지 설정
		Page page = new Page();
		
		page.setPageNo(Integer.parseInt((String) param.get("page")));
		page.setListSize(Integer.parseInt((String) param.get("perPage")));
		param.put("page", page);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", service.selectList(param));
		result.put("totalCnt", service.selectTotalCnt(param));
		
		return result;
	}
	
	@PutMapping("updateHits/{attractionId}")
	public boolean updateHits(@PathVariable("attractionId") int attractionId){
		return service.updateHits(attractionId);
	}
	
	@GetMapping("view/{attractionId}")
	public Map<String, Object> selectView(@PathVariable("attractionId") int attractionId) {
		return service.selectView(attractionId);
	}
	
	@PostMapping("favorite")
	public int updateFavorite(@RequestBody HashMap<String, Integer> param) {
		return service.updateFavorite(param);
	}
	
	@GetMapping("favorite/{userNo}")
	public int[] selectFavorite(@PathVariable("userNo") int userNo) {
		return service.selectFavorite(userNo);
	}
}
