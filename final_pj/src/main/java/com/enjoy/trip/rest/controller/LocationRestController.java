package com.enjoy.trip.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enjoy.trip.dto.Gugun;
import com.enjoy.trip.dto.Sido;
import com.enjoy.trip.service.LocationService;

@RestController
@RequestMapping("/api/location")
@CrossOrigin("*")
public class LocationRestController {
	private final LocationService service;

	public LocationRestController(LocationService service) {
		this.service = service;
	}
	
	@GetMapping("sido")
	public List<Sido> getSidoList() throws Exception{
		return service.getSidoList();
	}
	
	@GetMapping("gugun/{sidoCode}")
	public List<Gugun> getGugunList(@PathVariable("sidoCode") int sidoCode) throws Exception{
		return service.getGugunList(sidoCode);
	}
}
