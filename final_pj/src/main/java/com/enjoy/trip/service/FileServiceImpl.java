package com.enjoy.trip.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.enjoy.trip.dto.FileInfo;
import com.enjoy.trip.mapper.FileMapper;

@Service
public class FileServiceImpl implements FileService {

	private FileMapper fileMapper;
	
	@Value("${file.path}")
	private String filePath;
	
	public FileServiceImpl(FileMapper fileMapper) {
		this.fileMapper = fileMapper;
	}


	@Override
	public FileInfo selectFile(int fileNo) throws Exception {
		FileInfo file = fileMapper.selectFile(fileNo);
		return fileMapper.selectFile(fileNo);
	}


	@Override
	public String writeFile(List<MultipartFile> files) throws Exception {
		String fileKey = "";
		if (files != null) {
			// 폴더 없으면 폴더 생성
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			File folder = new File(filePath + File.separator + today);
			if (!folder.exists())
				folder.mkdir();

			for (MultipartFile file : files) {
				FileInfo fileinfo = new FileInfo();
				fileinfo.setOriginalFile(file.getOriginalFilename());
				fileinfo.setSaveFolder(today);
				fileinfo.setSaveFile(UUID.randomUUID().toString());
				file.transferTo(new File(folder, fileinfo.getSaveFile()));

				fileMapper.writeFile(fileinfo);
				fileKey += Integer.toString(fileinfo.getFileNo()) + " ";
			}
		}
		return fileKey;
	}


	@Override
	public void deleteFile(int fileNo) throws Exception {
		FileInfo file = fileMapper.selectFile(fileNo);
		
		File delF = new File(filePath + File.separator + file.getSaveFolder() + File.separator + file.getSaveFile());
		if(delF.exists())delF.delete();
		
		fileMapper.deleteFile(fileNo);
	}

}
