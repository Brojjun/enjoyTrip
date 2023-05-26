package com.enjoy.trip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.enjoy.trip.dto.AttractionInfo;

public interface AttractionService {
	List<AttractionInfo> selectList(HashMap<String, Object> param);
	int selectTotalCnt(HashMap<String, Object> param);
	Map<String, Object> selectView(int attractionId);
	int[] selectFavorite(int userNo);
	int updateFavorite(HashMap<String, Integer> param);
	boolean updateHits(int attractionId);
}
