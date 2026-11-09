package com.gona.app.data;

import java.util.List;

public class PageableComponentData {
	
	List<ComponentData> componentList;
	
	PageableData pageableData;

	public PageableComponentData(List<ComponentData> componentList, PageableData pageableData) {
		this.componentList = componentList;
		this.pageableData = pageableData;
	}

	public List<ComponentData> getComponentList() {
		return componentList;
	}

	public void setComponentList(List<ComponentData> componentList) {
		this.componentList = componentList;
	}

	public PageableData getPageableData() {
		return pageableData;
	}

	public void setPageableData(PageableData pageableData) {
		this.pageableData = pageableData;
	}

}