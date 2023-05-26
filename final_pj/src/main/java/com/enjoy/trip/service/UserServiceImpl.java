package com.enjoy.trip.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.enjoy.trip.config.MailManager;
import com.enjoy.trip.config.SHA256Manager;
import com.enjoy.trip.dto.MailDto;
import com.enjoy.trip.dto.User;
import com.enjoy.trip.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	private final UserMapper mapper;
	private final MailManager mailManager;

	public UserServiceImpl(UserMapper mapper, MailManager mailManager) {
		this.mapper = mapper;
		this.mailManager = mailManager;
	}

	@Override
	public User login(User param) throws Exception {
		return mapper.login(param);
	}

	@Override
	public int signUp(User param) throws Exception {
		return mapper.signUp(param);
	}

	@Override
	public boolean checkDuplicateId(String id) {

		int cnt = mapper.checkDuplicateId(id);
		return cnt == 0;
	}

	@Override
	public void updateRefreshToken(User user) {
		mapper.updateRefreshToken(user);
	}

	@Override
	public User getUserInfo(HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		return mapper.getUserInfo(param);
	}

	@Override
	public Map<String, Object> findId(User param) {
		Map<String, Object> result = new HashMap<>();
		result.put("result", false);
		
		String id = mapper.findId(param);
		
		if(id != null && !id.equals("")) {
			result.put("result", true);
			result.put("data", id);
		}
		return result;
	}
	
	@Override
	public boolean findPw(User param) throws Exception {
		HashMap<String, Object> user = mapper.findPw(param);
		//조회된 회원이 있는 경우
		if(user != null) {
			String temp = getTempPassword();
			String email = param.getEmail0() + "@" + param.getEmail1();
			
			MailDto message = new MailDto(email, "임시 비밀번호 메일입니다.", "임시비밀번호는 " + temp + "입니다.");
			
			mailManager.oneSendMail(message);
			
			temp = SHA256Manager.encrypt(temp);
			user.put("tempPassword", temp);
			mapper.updateTempPassword(user);
			return true;
		} else return false;
	}
	
	private String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@', '#', '$', '%', '^' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

	@Override
	public boolean updateUser(User params) {
		return mapper.updateUser(params) > 0;
	}
	
}
