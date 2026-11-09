package com.gona.app.data;

import java.util.List;

public class PageablePageData {
	
	List<PageData> pageList;
	
	PageableData pageableData;
	
	public PageablePageData(List<PageData> pageList, PageableData pageableData) {
		super();
		this.pageList = pageList;
		this.pageableData = pageableData;
	}

	public List<PageData> getPageList() {
		return pageList;
	}

	public void setPageList(List<PageData> pageList) {
		this.pageList = pageList;
	}

	public PageableData getPageableData() {
		return pageableData;
	}

	public void setPageableData(PageableData pageableData) {
		this.pageableData = pageableData;
	}
	
	

}
