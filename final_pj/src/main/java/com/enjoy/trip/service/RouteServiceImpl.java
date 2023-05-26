package com.enjoy.trip.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enjoy.trip.dto.AttractionInfo;
import com.enjoy.trip.dto.MyRoute;
import com.enjoy.trip.mapper.RouteMapper;

@Service
public class RouteServiceImpl implements RouteService{
	private final RouteMapper mapper;
	private final AttractionService attractionService;

	public RouteServiceImpl(RouteMapper mapper, AttractionService attractionService) {
		this.mapper = mapper;
		this.attractionService = attractionService;
	}

	@Override
	public List<MyRoute> selectList(HashMap<String, Object> param) {
		return mapper.selectList(param);
	}
	
	@Override
	public int selectTotalCnt(HashMap<String, Object> param) {
		return mapper.selectTotalCnt(param);
	}
	
	@Override
	public Map<String, Object> selectView(int routeNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", false);
		MyRoute data = mapper.selectView(routeNo);
		List<AttractionInfo> list = new ArrayList<>();
		for(int i=0; i < data.getOrders().size(); i++) list.add((AttractionInfo) attractionService.selectView(data.getOrders().get(i).getAttractionId()).get("data"));
		
		data.setOrderInfo(list);
		if(data != null) {
			result.put("result", true);
			result.put("data", data);
		} 
		return result;
	}
	
	@Override
	public boolean updateHits(int routeNo) {
		return mapper.updateHits(routeNo) > 0;
	}
	
	@Override
	public boolean insertRoute(MyRoute param) {
		return mapper.insertRoute(param) != 0;
	}

	@Override
	public boolean updateRoute(MyRoute param) {
		//루트 내용 업데이트
		int cnt = mapper.updateRoute(param);
		if(cnt == 0) return false;
		
		//이전 경로 설정 삭제
		mapper.deleteOrders(param.getNo());
		
		//새로운 경로 추가
		mapper.insertOrders(param);
		
		return true;
	}
	
	@Transactional
	@Override
	public boolean deleteRoute(int routeNo) {
		int cnt = mapper.deleteRoute(routeNo);
		if(cnt == 0) return false;
		mapper.deleteOrders(routeNo);
		return true;
	}

	@Override
	public int scrapRoute(int routeNo, int userNo) {

		HashMap<String, Integer> param = new HashMap<>();
		param.put("routeNo", routeNo);
		param.put("userNo", userNo);
		
		//본인의 게시물을 스크랩하려고 할때
		if(mapper.checkRoute(param) == 1) return 2;
		
		MyRoute param2 = (MyRoute) selectView(routeNo).get("data");
		param2.setMemberNo(userNo);
		if(!insertRoute(param2)) return 0;
		
		mapper.updateScrapCnt(routeNo);
		
		return 1;
	}

}
