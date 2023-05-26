package com.enjoy.trip.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.enjoy.trip.dto.AttractionInfo;

@Mapper
public interface AttractionMapper {
	List<AttractionInfo> selectList(Map<String, Object> param);
	int selectTotalCnt(HashMap<String, Object> param);
	AttractionInfo selectView(int contentId);
	int[] selectFavorite(int userNo);
	int insertFavorite(HashMap<String, Integer> param);
	int deleteFavorite(HashMap<String, Integer> param);
	int updateHits(int attractionId);
}
