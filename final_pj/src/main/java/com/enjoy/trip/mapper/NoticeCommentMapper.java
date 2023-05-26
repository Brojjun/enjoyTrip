package com.enjoy.trip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.enjoy.trip.dto.NoticeComment;

@Mapper
public interface NoticeCommentMapper {
	
	List<NoticeComment> selectComment(int noticeNo) throws Exception;
	
	void writeComment(NoticeComment comment) throws Exception;
	
	void deleteComment(NoticeComment comment) throws Exception;
	
	void updateComment(NoticeComment comment) throws Exception;
	
	
}
