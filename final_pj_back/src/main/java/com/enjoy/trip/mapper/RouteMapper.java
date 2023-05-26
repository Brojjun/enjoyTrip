package com.enjoy.trip.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.enjoy.trip.dto.MyRoute;

@Mapper
public interface RouteMapper {
	List<MyRoute> selectList(HashMap<String, Object> param);
	int selectTotalCnt(HashMap<String, Object> param);
	int insertRoute(MyRoute param);
	int updateRoute(MyRoute param);
	MyRoute selectView(int routeNo);
	int deleteRoute(int routeNo);
	void deleteOrders(int routeNo);
	void insertOrders(MyRoute param);
	int checkRoute(HashMap<String, Integer> param);
	void updateScrapCnt(int routeNo);
	int updateHits(int routeNo);
}
