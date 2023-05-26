package com.enjoy.trip.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.enjoy.trip.dto.FileInfo;
import com.enjoy.trip.dto.Notice;
import com.enjoy.trip.dto.NoticeLike;

@Mapper
public interface NoticeMapper {
	List<Notice> selectNotice(HashMap<String,Integer> map);
	void writeNotice(Notice param) throws Exception;
	void deleteNotice(int noticeNo) throws Exception;
	void updateNotice(Notice notice) throws Exception;
	Notice getNotice(int noticeNo) throws Exception;
	void hitsRateUp(int noticeNo) throws Exception;

	void removeNotice(int noticeNo) throws Exception;

	int selectTotalCnt() throws Exception;
	
	int writeFile(FileInfo fileinfo) throws Exception;
	
	void writeLike(NoticeLike noticeLike) throws Exception;

	void deleteLike(NoticeLike noticeLike) throws Exception;
	
	int noticeLikeCnt(int noticeNo) throws Exception;
	
	int userLikeSelect(NoticeLike noticeLike) throws Exception;

}
