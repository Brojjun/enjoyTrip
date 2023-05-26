package com.enjoy.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enjoy.trip.dto.ShareAttraction;
import com.enjoy.trip.dto.ShareBoard;
import com.enjoy.trip.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	private BoardMapper boardMapper;	
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public List<ShareBoard> selectBoard() throws Exception {
		return boardMapper.selectBoard();
	}
	
	@Override
	public void deleteBoard(int boardNo) throws Exception {
		boardMapper.deleteBoard(boardNo);
	}

	@Override
	public void updateBoard(ShareBoard update) throws Exception {
		boardMapper.updateBoard(update);
	}

	@Override
	public void writeBoard(ShareBoard write) throws Exception {
		boardMapper.writeBoard(write);
	}

	@Override
	public ShareBoard getBoard(int boardNo) throws Exception {
		boardMapper.hitsRateUp(boardNo);
		return boardMapper.getBoard(boardNo);
	}

	@Override
	public List<ShareAttraction> getShareAttraction(int userNo) throws Exception {
		return boardMapper.getShareAttraction(userNo);
	}

}
