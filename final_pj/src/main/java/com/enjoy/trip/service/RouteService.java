package com.enjoy.trip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.enjoy.trip.dto.MyRoute;

public interface RouteService {
	List<MyRoute> selectList(HashMap<String, Object> param);
	int selectTotalCnt(HashMap<String, Object> param);
	Map<String, Object> selectView(int routeNo);
	boolean insertRoute(MyRoute param);
	boolean updateRoute(MyRoute param);
	boolean deleteRoute(int routeNo);
	int scrapRoute(int routeNo, int userNo);
	boolean updateHits(int routeNo);
}
