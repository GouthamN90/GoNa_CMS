package com.gona.app.data;

public class PageableData {
	
	int currentPage;
	int totalPages;
	long totalItems;
	int pageSize;
	SortBy sortBy;
	SortOrder sortDir;
	
	public PageableData(int currentPage, int totalPages, long totalItems, int pageSize, SortBy sortBy, SortOrder sortDir) {
		super();
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.totalItems = totalItems;
		this.pageSize = pageSize;
		this.sortBy = sortBy;
		this.sortDir = sortDir;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
	
	public SortBy getSortBy() {
		return sortBy;
	}

	public void setSortBy(SortBy sortBy) {
		this.sortBy = sortBy;
	}

	public SortOrder getSortDir() {
		return sortDir;
	}

	public void setSortDir(SortOrder sortDir) {
		this.sortDir = sortDir;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
