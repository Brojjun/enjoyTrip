package com.enjoy.trip.rest.controller;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enjoy.trip.dto.FileInfo;
import com.enjoy.trip.dto.Notice;
import com.enjoy.trip.service.FileService;
import com.enjoy.trip.service.NoticeService;

@RestController
@RequestMapping("/api/download")
@CrossOrigin("*")
public class DownLoadRestController {
	
	@Value("${file.path}")
	private String uploadPath;
	private FileService fileService;
	
	
	public DownLoadRestController(FileService fileService) {
		super();
		this.fileService = fileService;
	}

	@GetMapping("/{no}")
	public ResponseEntity<Object> download(@PathVariable("no") int fileNo) throws Exception{
		FileInfo fileinfo = fileService.selectFile(fileNo);
		String file = uploadPath + File.separator + fileinfo.getSaveFolder() + File.separator + fileinfo.getSaveFile();
		try {
		Path filePath = Paths.get(file);
		Resource resource = new InputStreamResource(Files.newInputStream(filePath));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(URLEncoder.encode(fileinfo.getOriginalFile(), "UTF-8").replaceAll("\\+", "%20")).build());
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}
	
}
