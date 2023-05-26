package com.enjoy.trip.service;

import java.util.List;

import com.enjoy.trip.dto.NoticeComment;

public interface NoticeCommentService {
	
	List<NoticeComment> selectComment(int noticeNo) throws Exception;
	
	void writeComment(NoticeComment comment) throws Exception;
	
	void deleteComment(NoticeComment comment) throws Exception;
	
	void updateComment(NoticeComment comment) throws Exception;
	


}
