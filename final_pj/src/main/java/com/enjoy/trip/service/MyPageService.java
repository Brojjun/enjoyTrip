package com.enjoy.trip.service;

import com.enjoy.trip.dto.User;

public interface MyPageService {
	//마이페이지에서 사용하기 위한 
	User getUser(int userNo);
	int updateUser(User param);
}
