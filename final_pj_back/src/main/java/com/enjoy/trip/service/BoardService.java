package com.enjoy.trip.service;

import java.util.List;

import com.enjoy.trip.dto.ShareAttraction;
import com.enjoy.trip.dto.ShareBoard;

public interface BoardService {
	List<ShareBoard> selectBoard() throws Exception;

	void deleteBoard(int boardNo) throws Exception;

	void writeBoard(ShareBoard write) throws Exception;

	ShareBoard getBoard(int boardNo) throws Exception;

	void updateBoard(ShareBoard update) throws Exception;

	List<ShareAttraction> getShareAttraction(int userNo) throws Exception;
}
