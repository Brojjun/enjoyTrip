package com.enjoy.trip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.enjoy.trip.dto.AttractionInfo;
import com.enjoy.trip.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService {
	private final AttractionMapper mapper;
	
	public AttractionServiceImpl(AttractionMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<AttractionInfo> selectList(HashMap<String, Object> param) {
		return mapper.selectList(param);
	}
	
	@Override
	public int selectTotalCnt(HashMap<String, Object> param) {
		return mapper.selectTotalCnt(param);
	}

	@Override
	public Map<String, Object> selectView(int attractionId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", false);
		AttractionInfo data = mapper.selectView(attractionId);
		if(data != null) {
			result.put("result", true);
			result.put("data", data);
		} 
		return result;
	}

	@Override
	public int[] selectFavorite(int userNo) {
		return mapper.selectFavorite(userNo);
	}

	@Override
	public int updateFavorite(HashMap<String, Integer> param) {
		int result = 0;
		if(param.get("flag") == 0) result = mapper.insertFavorite(param);
		else result = mapper.deleteFavorite(param);
		
		return result;
	}

	@Override
	public boolean updateHits(int attractionId) {
		return mapper.updateHits(attractionId) > 0;
	}

}
