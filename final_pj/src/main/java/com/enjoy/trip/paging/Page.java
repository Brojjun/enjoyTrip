package com.enjoy.trip.paging;

public class Page {
	private int pageNo;
	private int listSize = 10;

	public Page() {
		this(1, 10);
	}

	public Page(int pageNo) {
		this(pageNo, 10);
	}

	public Page(int pageNo, int listSize) {
		this.pageNo = pageNo;
		this.listSize = 10;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getBegin() {
		return (pageNo - 1) * listSize;
	}
}