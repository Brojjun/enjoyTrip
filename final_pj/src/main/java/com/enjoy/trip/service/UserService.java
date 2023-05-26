package com.enjoy.trip.service;

import java.util.HashMap;
import java.util.Map;

import com.enjoy.trip.dto.User;

public interface UserService {

	User login(User param) throws Exception;
	int signUp(User param) throws Exception;
	boolean findPw(User param) throws Exception;
	boolean checkDuplicateId(String id);
	void updateRefreshToken(User user);
	User getUserInfo(HashMap<String, Object> param);
	Map<String, Object> findId(User param);
	boolean updateUser(User params);
}
