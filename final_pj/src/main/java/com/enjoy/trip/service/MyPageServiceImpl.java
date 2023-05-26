package com.enjoy.trip.service;

import org.springframework.stereotype.Service;

import com.enjoy.trip.dto.User;
import com.enjoy.trip.mapper.MypageMapper;

@Service
public class MyPageServiceImpl implements MyPageService {
	private final MypageMapper mapper;

	public MyPageServiceImpl(MypageMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public User getUser(int userNo) {
		return mapper.getUser(userNo);
	}

	@Override
	public int updateUser(User param) {
		return mapper.updateUser(param);
	}

}
