package com.enjoy.trip.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enjoy.trip.dto.NoticeComment;
import com.enjoy.trip.service.NoticeCommentService;



@RestController
@RequestMapping("/api/noticeComment")
@CrossOrigin("*")
public class NoticeCommentRestController {
	private NoticeCommentService noticeCommentService;
	public NoticeCommentRestController(NoticeCommentService noticeCommentService) {
		this.noticeCommentService = noticeCommentService;
	}
	
	@GetMapping("/list/{noticeNo}")
	public List<NoticeComment> select(@PathVariable int noticeNo) throws Exception {
		return noticeCommentService.selectComment(noticeNo);
	}
	

	@PostMapping("/write")
	public void writeComment(@RequestBody NoticeComment comment) throws Exception{
		noticeCommentService.writeComment(comment);
	}

	@PutMapping("/delete")
	public void deleteComment(@RequestBody NoticeComment comment) throws Exception{
		noticeCommentService.deleteComment(comment);
	}
	
	@PutMapping("/update")
	public void updateComment(NoticeComment comment) throws Exception{
		noticeCommentService.updateComment(comment);
	}

}
