package com.enjoy.trip.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.enjoy.trip.dto.FileInfo;

public interface FileService {

	FileInfo selectFile(int fileNo) throws Exception;

	String writeFile(List<MultipartFile> files) throws Exception;
	
	void deleteFile(int no) throws Exception;
}
