package com.enjoy.trip.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.enjoy.trip.dto.FileInfo;
import com.enjoy.trip.dto.Notice;

@Mapper
public interface FileMapper {

	FileInfo selectFile(int fileNo) throws Exception;
	
	int writeFile(FileInfo fileinfo) throws Exception;
	
	void deleteFile(int fileNo) throws Exception;
}
