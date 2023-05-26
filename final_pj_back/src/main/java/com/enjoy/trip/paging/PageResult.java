package com.enjoy.trip.paging;

// 페이징 관련 데이터 처리 클래스
public class PageResult {
	private int pageNo;
	private int count;
	private int tabSize;
	private int listSize;
	private int beginPage;
	private int endPage;
	private boolean prev;
	private boolean next;

	public PageResult(int pageNo, int count) {
		this(pageNo, count, 10, 10);
	}

	public PageResult(int pageNo, int count, int listSize, int tabSize) {
		this.pageNo = pageNo;
		this.count = count;
		this.listSize = listSize;
		this.tabSize = tabSize;

		int lastPage = (int) Math.ceil(count / 10d);
		int currTab = (pageNo - 1) / tabSize + 1;
		this.beginPage = (currTab - 1) * tabSize + 1;
		this.endPage = (currTab * tabSize > lastPage) ? lastPage : currTab * tabSize;

		this.prev = beginPage != 1;
		this.next = endPage != lastPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTabSize() {
		return tabSize;
	}

	public void setTabSize(int tabSize) {
		this.tabSize = tabSize;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

}
