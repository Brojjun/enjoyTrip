package com.enjoy.trip.service;

import java.util.List;

import com.enjoy.trip.dto.Gugun;
import com.enjoy.trip.dto.Sido;

public interface LocationService {
	List<Sido> getSidoList() throws Exception;
	List<Gugun> getGugunList(int sidoCode) throws Exception;
}
