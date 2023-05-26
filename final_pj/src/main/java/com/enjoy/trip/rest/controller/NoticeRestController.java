package com.enjoy.trip.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enjoy.trip.dto.Notice;
import com.enjoy.trip.dto.NoticeLike;
import com.enjoy.trip.service.NoticeService;

@RestController
@RequestMapping("/api/notice")
@CrossOrigin("*")
public class NoticeRestController {
	private NoticeService noticeService;
	public NoticeRestController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@GetMapping("/list/{pageNo}")
	public List<Notice> select(@PathVariable int pageNo) throws Exception {
		return noticeService.selectNotice(pageNo);
	}
	
	@GetMapping("/articleCnt")
	public int totalCnt() throws Exception{
		return noticeService.selectTotalCnt();
	}
	
	@PostMapping("/write")
	public boolean writeNotice(Notice notice, List<MultipartFile> files) throws Exception {
		//User user = ((User) session.getAttribute("userInfo"));
		//if(user.getIsAdmin() != 1) return; //1인 경우 어드민 설정
		//int userNo = user.getNo();
		
		// 사용자 no 세션에서 가져와서 입력
		//param.setUserNo(userNo);
		notice.setUserNo(notice.getNo());
		
		noticeService.writeNotice(notice, files);
		return true;
	}
	
	@DeleteMapping("/delete/{noticeNo}")
	public boolean deleteNotice(@PathVariable int noticeNo) throws Exception {
		noticeService.removeNotice(noticeNo);
		return true;
	}

	@PutMapping("/delete/{noticeNo}")
	public void UpdateNotice(@PathVariable int noticeNo) throws Exception {
		noticeService.deleteNotice(noticeNo);

	}

	@PutMapping("/update/{noticeNo}")
	public boolean updateNotice(Notice notice, List<MultipartFile> files, String delFile, String liveFile, @PathVariable int noticeNo) throws Exception {
		notice.setNo(noticeNo);
		noticeService.updateNotice(notice, files, delFile, liveFile);
		return true;	
	}
	
	@GetMapping("/info/{noticeNo}")
	public Notice getNotice(@PathVariable int noticeNo) throws Exception {
		return noticeService.getNotice(noticeNo);
	}
	
	@PutMapping("/hit/{noticeNo}")
	public void upHit(@PathVariable int noticeNo) throws Exception {
		noticeService.addHit(noticeNo);
	}
	
	@PostMapping("/like")
	public void wirteLike(@RequestBody NoticeLike noticeLike) throws Exception{
		noticeService.writeLike(noticeLike);
	}
	
	@DeleteMapping("/like/{noticeNo}/{userNo}")
	public void deleteLike(@PathVariable int noticeNo, @PathVariable int userNo) throws Exception{
		NoticeLike notice = new NoticeLike();
		notice.setUserNo(userNo);
		notice.setnoticeNo(noticeNo);
		noticeService.deleteLike(notice);
	}
	
	@GetMapping("/like/cnt/{noticeNo}")
	public int noticeLikeCnt(@PathVariable int noticeNo) throws Exception{
		return noticeService.noticeLikeCnt(noticeNo);
	}
	
	@GetMapping("/like/{userNo}/{noticeNo}")
	public int userLikeSelect(@PathVariable int userNo, @PathVariable int noticeNo) throws Exception{
		NoticeLike notice = new NoticeLike();
		notice.setUserNo(userNo);
		notice.setnoticeNo(noticeNo);
		boolean ans = noticeService.userLikeSelect(notice);
		if(ans) return 1;
		else return 0;
	}
	
	
}
