package com.enjoy.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enjoy.trip.dto.Gugun;
import com.enjoy.trip.dto.Sido;
import com.enjoy.trip.mapper.LocationMapper;

@Service
public class LocationServiceImpl implements LocationService{
	private final LocationMapper mapper;

	public LocationServiceImpl(LocationMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Sido> getSidoList() throws Exception{
		return mapper.getSidoList();
	}

	@Override
	public List<Gugun> getGugunList(int sidoCode) throws Exception {
		return mapper.getGugunList(sidoCode);
	}
	
}
