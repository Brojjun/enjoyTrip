package com.enjoy.trip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.enjoy.trip.dto.ShareBoard;
import com.enjoy.trip.dto.ShareAttraction;
import com.enjoy.trip.dto.User;

@Mapper
public interface BoardMapper {
	List<ShareBoard> selectBoard() throws Exception;
	ShareBoard getBoard(int boardNo) throws Exception;
	void deleteBoard(int boardNo) throws Exception;
	void updateBoard(ShareBoard update) throws Exception;
	void writeBoard(ShareBoard write) throws Exception;
	List<ShareAttraction> getShareAttraction(int userNo) throws Exception;
	void hitsRateUp(int boardNo) throws Exception;
}
