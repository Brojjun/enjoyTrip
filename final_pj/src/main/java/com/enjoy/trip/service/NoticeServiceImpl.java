package com.enjoy.trip.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.enjoy.trip.dto.FileInfo;
import com.enjoy.trip.dto.Notice;
import com.enjoy.trip.dto.NoticeLike;
import com.enjoy.trip.mapper.FileMapper;
import com.enjoy.trip.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	private NoticeMapper noticeMapper;
	private FileService fileService;

	@Value("${file.path}")
	private String filePath;

	public NoticeServiceImpl(NoticeMapper noticeMapper, FileService fileService) {
		super();
		this.noticeMapper = noticeMapper;
		this.fileService = fileService;
	}

	@Override
	public List<Notice> selectNotice(int pageNo) {
		int amount = 10; // 페이지 당 게시물 단위
		pageNo = (pageNo - 1) * amount;

		HashMap<String, Integer> map = new HashMap<>();
		map.put("pageNo", pageNo);
		map.put("amount", amount);
		return noticeMapper.selectNotice(map);
	}

	@Override
	public void writeNotice(Notice notice, List<MultipartFile> files) throws Exception {
		String fileData = fileService.writeFile(files);
		notice.setFileData(fileData);
		noticeMapper.writeNotice(notice);
	}

	@Override
	public void deleteNotice(int noticeNo) throws Exception {
		// 해당 게시판의 데이터 정보 얻어오기
		Notice del = noticeMapper.getNotice(noticeNo);
		// 파일 삭제하기
		if (del.getFileData() != null) {
			StringTokenizer st = new StringTokenizer(del.getFileData());
			while (st.hasMoreTokens()) {
				fileService.deleteFile(Integer.parseInt(st.nextToken()));
			}
		}
		// db에서 삭제
		noticeMapper.deleteNotice(noticeNo);
	}

	@Override
	public void updateNotice(Notice notice, List<MultipartFile> files, String delFile, String liveFile) throws Exception {
		String nowFile = "";
		//파일 삭제
		if(delFile != null) {
			StringTokenizer st = new StringTokenizer(delFile, ",");
			while(st.hasMoreTokens()) {
				fileService.deleteFile(Integer.parseInt(st.nextToken().trim()));
			}
		}
		//삭제하지 않는 파일 문자열 생성
		if(liveFile != null) {
			StringTokenizer st = new StringTokenizer(liveFile, ",");
			while(st.hasMoreTokens()) {
				nowFile += st.nextToken().trim();
				nowFile += " ";
			}
		}
		
		//새로 저장된 파일 저장
		nowFile += fileService.writeFile(files);

		notice.setFileData(nowFile);
		noticeMapper.updateNotice(notice);

		
//		// 해당 게시판의 데이터 정보 얻어오기
//		Notice del = noticeMapper.getNotice(notice.getNo());
//		notice.setFileData(del.getFileData());
//
//		// files가 있다면
//		if (delFile != null) {
//			// 기존 file 삭제
//			if (del.getFileData() != null) {
//				StringTokenizer st = new StringTokenizer(del.getFileData());
//				while (st.hasMoreTokens()) {
//					fileService.deleteFile(Integer.parseInt(st.nextToken()));
//				}
//			}
//
//			// 새로운 파일 추가
//			String fileData = fileService.writeFile(files);
//			notice.setFileData(fileData);
//		}
//
//		noticeMapper.updateNotice(notice);
	}

	@Override
	public Notice getNotice(int noticeNo) throws Exception {
		Notice notice = noticeMapper.getNotice(noticeNo);

		if (notice.getFileData() != null) {
			StringTokenizer st = new StringTokenizer(notice.getFileData());
			int arr[] = new int[st.countTokens()];
			FileInfo[] fileinfo = new FileInfo[st.countTokens()];
			int i = 0;

			while (st.hasMoreTokens()) {
				arr[i] = Integer.parseInt(st.nextToken().trim());
				fileinfo[i] = fileService.selectFile(arr[i]);
				i++;
			}
			notice.setFile(arr);
			notice.setFileInfo(fileinfo);
		}
		return notice;
	}

	@Override
	public void removeNotice(int noticeNo) throws Exception {
		noticeMapper.removeNotice(noticeNo);

	}

	@Override
	public int selectTotalCnt() throws Exception {
		return noticeMapper.selectTotalCnt();
	}

	@Override
	public void addHit(int noticeNo) throws Exception {
		noticeMapper.hitsRateUp(noticeNo);
	}

	@Override
	public void writeLike(NoticeLike noticeLike) throws Exception {
		noticeMapper.writeLike(noticeLike);
	}

	@Override
	public void deleteLike(NoticeLike noticeLike) throws Exception {
		noticeMapper.deleteLike(noticeLike);
		
	}

	@Override
	public int noticeLikeCnt(int noticeNo) throws Exception {
		return noticeMapper.noticeLikeCnt(noticeNo);
	}

	@Override
	public boolean userLikeSelect(NoticeLike noticeLike) throws Exception {
		return noticeMapper.userLikeSelect(noticeLike) != 0;
	}

}
