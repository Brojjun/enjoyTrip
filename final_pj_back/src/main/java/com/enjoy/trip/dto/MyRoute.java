package com.enjoy.trip.dto;

import java.util.List;

public class MyRoute {
	private int no;
	private int memberNo;
	private String writerName;
	private String title;
	private String contents;
	private int scrapCnt;
	private int hits;
	private List<RouteOrders> orders;
	private List<AttractionInfo> orderInfo;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getScrapCnt() {
		return scrapCnt;
	}
	public void setScrapCnt(int scrapCnt) {
		this.scrapCnt = scrapCnt;
	}
	public List<RouteOrders> getOrders() {
		return orders;
	}
	public void setOrders(List<RouteOrders> orders) {
		this.orders = orders;
	}
	public List<AttractionInfo> getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(List<AttractionInfo> orderInfo) {
		this.orderInfo = orderInfo;
	}
}
