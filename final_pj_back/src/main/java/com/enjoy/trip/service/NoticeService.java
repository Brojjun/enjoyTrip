package com.enjoy.trip.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.enjoy.trip.dto.Notice;
import com.enjoy.trip.dto.NoticeLike;

public interface NoticeService {

	List<Notice> selectNotice(int pageNo) throws Exception;

	void writeNotice(Notice param, List<MultipartFile> files) throws Exception;

	void deleteNotice(int noticeNo) throws Exception;

	void updateNotice(Notice param, List<MultipartFile> files, String delFile, String liveFile) throws Exception;

	Notice getNotice(int noticeNo) throws Exception;

	void removeNotice(int noticeNo) throws Exception;

	int selectTotalCnt() throws Exception;

	void addHit(int noticeNo) throws Exception;


	void writeLike(NoticeLike noticeLike) throws Exception;

	void deleteLike(NoticeLike noticeLike) throws Exception;
	
	int noticeLikeCnt(int noticeNo) throws Exception;
	
	boolean userLikeSelect(NoticeLike noticeLike) throws Exception;

}
