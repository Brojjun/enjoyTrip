package com.enjoy.trip.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.enjoy.trip.dto.ShareAttraction;

@Mapper
public interface ShareAttractionMapper {
	void writeShareAttraction(ShareAttraction param) throws Exception;
	List<ShareAttraction> selectShareAttraction() throws Exception;
	void deleteShareAttraction(int shareNo) throws Exception;
}
