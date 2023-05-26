package com.enjoy.trip.dto;

public class Notice {
	private int no;
	private String title;
	private String contents;
	private int hits;
	private int userNo;
	private String writerName;
	private String registDate;
	private String registDateStr;
	private String updateDate;
	private String removeDate;
	private FileInfo []fileInfo;
	private String fileData;
	private int[] file;
	private int likeCnt;
	
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public FileInfo[] getFileInfo() {
		return this.fileInfo;
	}
	public void setFileInfo(FileInfo[] fileInfo) {
		this.fileInfo = fileInfo;
	}
	
	public int[] getFile() {
		return file;
	}
	public void setFile(int[] file) {
		this.file = file;
	}
	public String getFileData() {
		return fileData;
	}
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public String getRegistDateStr() {
		return registDateStr;
	}
	public void setRegistDateStr(String registDateStr) {
		this.registDateStr = registDateStr;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getRemoveDate() {
		return removeDate;
	}
	public void setRemoveDate(String removeDate) {
		this.removeDate = removeDate;
	}	
	
}