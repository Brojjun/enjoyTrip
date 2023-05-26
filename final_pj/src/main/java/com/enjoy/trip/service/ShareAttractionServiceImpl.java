package com.enjoy.trip.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.enjoy.trip.dto.ShareAttraction;
import com.enjoy.trip.mapper.ShareAttractionMapper;

@Service
public class ShareAttractionServiceImpl implements ShareAttractionService {
	private ShareAttractionMapper shareBoardMapper;
	public ShareAttractionServiceImpl(ShareAttractionMapper shareBoardMapper) {
		this.shareBoardMapper = shareBoardMapper;
	}
	@Override
	public void writeShareAttraction(ShareAttraction param) throws Exception {
		shareBoardMapper.writeShareAttraction(param);
	}
	@Override
	public List<ShareAttraction> selectShareAttraction() throws Exception {
		return shareBoardMapper.selectShareAttraction();
	}
	@Override
	public void deleteShareAttraction(int shareNo) throws Exception {
		shareBoardMapper.deleteShareAttraction(shareNo);
		
	}
	
}
