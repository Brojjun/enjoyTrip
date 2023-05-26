package com.enjoy.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enjoy.trip.dto.NoticeComment;
import com.enjoy.trip.mapper.NoticeCommentMapper;

@Service
public class NoticeCommentServiceImpl implements NoticeCommentService {
	
	NoticeCommentMapper noticeCommentMapper;
	
	
	public NoticeCommentServiceImpl(NoticeCommentMapper noticeCommentMapper) {
		super();
		this.noticeCommentMapper = noticeCommentMapper;
	}

	
	@Override
	public List<NoticeComment> selectComment(int noticeNo) throws Exception {
		return noticeCommentMapper.selectComment(noticeNo);
	}

	@Override
	public void writeComment(NoticeComment comment) throws Exception {
		noticeCommentMapper.writeComment(comment);
	}

	@Override
	public void deleteComment(NoticeComment comment) throws Exception {
		noticeCommentMapper.deleteComment(comment);
		
	}

	@Override
	public void updateComment(NoticeComment comment) throws Exception {
		noticeCommentMapper.updateComment(comment);
		
	}

	

}
