package com.enjoy.trip.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enjoy.trip.config.JwtManager;
import com.enjoy.trip.config.SHA256Manager;
import com.enjoy.trip.dto.User;
import com.enjoy.trip.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserRestController {
	
	private JwtManager jwtManager;
	
	private final UserService service;
	
	public UserRestController(UserService service) {
		this.jwtManager = new JwtManager();
		this.service = service;
	}

	@PostMapping("login")
	public HashMap<String, Object> loginAction(@RequestBody User param) throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		
		//암호화
		param.setPassword(SHA256Manager.encrypt(param.getPassword()));
		
		User user = service.login(param);
		if(user != null) {
			result.put("result", true);
			result.put("accessToken", jwtManager.generateJwtToken(user));
			String refreshToken = jwtManager.generateRefreshJwtToken(user);
			user.setRefreshToken(refreshToken);
			service.updateRefreshToken(user);
			result.put("refreshToken", refreshToken);
		} else {
			result.put("result", false);
		}
		
		return result;
	}
	
	@GetMapping("info/{userId}")
	public HashMap<String, Object> getUserInfo(@PathVariable("userId") String userId, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("result", false);
		
		HashMap<String, Object> param = new HashMap<>();
		int userNo = (int) request.getAttribute("no");
		param.put("id", userId);
		param.put("no", userNo);
		
		User user = service.getUserInfo(param);
		if(user != null) {
			result.put("result", true);
			result.put("userInfo", user);
		}
		
		return result;
	}
	
	//아이디중복확인
	@GetMapping("checkDuplicateId/{userId}")
	public boolean checkDuplicateId(@PathVariable("userId") String id) {
		return service.checkDuplicateId(id);
	}
	
	//회원가입
	@PostMapping("signUp")
	public boolean signUpAction(@RequestBody User param) throws Exception {
		boolean result = false;
		//암호화
		param.setPassword(SHA256Manager.encrypt(param.getPassword()));
		if(service.signUp(param) == 1) result = true;
		return result;
	}
	
	//아이디
	@PostMapping("findId")
	public Map<String, Object> findIdAction(@RequestBody User param) throws Exception {
		return service.findId(param);
	}
	
	//비밀번호찾기
	@PostMapping("findPw")
	public boolean findPwAction(@RequestBody User param) throws Exception {
		return service.findPw(param);
	}
	
	@PostMapping("refresh")
	public HashMap<String, Object> refreshToken(@RequestBody User param, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("result", false);
		
		//유효기간 만료가 아닌 경우
		if(request.getAttribute("no") != null) {
			int userNo = (int) request.getAttribute("no");
			if(userNo == param.getNo()) {
				result.put("accessToken", jwtManager.generateJwtToken(param));
				result.put("result", true);
			}
		}
		
		return result;
	}
	
	//로그아웃
	@GetMapping("logout/{userNo}")
	public HashMap<String, Object> logout(@PathVariable("userNo") int userNo) throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		result.put("result", true);
		return result;
	}
	
	@PutMapping("update")
	public boolean updateUser(@RequestBody User params) {
		return service.updateUser(params);
	}
}
