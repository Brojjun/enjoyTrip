package com.enjoy.trip.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.enjoy.trip.dto.User;

@Mapper
public interface UserMapper {
	User login(User param);
	int checkDuplicateId(String id);
	void updateRefreshToken(User user);
	User getUserInfo(HashMap<String, Object> param);
	int signUp(User param);
	String findId(User param);
	HashMap<String, Object> findPw(User param);
	void updateTempPassword(HashMap<String, Object> param);
	int updateUser(User params);
}
