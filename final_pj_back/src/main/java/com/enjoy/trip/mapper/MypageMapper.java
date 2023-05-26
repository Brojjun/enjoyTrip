package com.enjoy.trip.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.enjoy.trip.dto.User;

@Mapper
public interface MypageMapper {
	User getUser(int userNo);
	int updateUser(User param);
}
