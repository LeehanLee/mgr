package com.infi.model.dto;

public class AccountQueryDto {

	private int page;
	private int pageSize;
	private String username;
	
	public int getSkip(){
		if(page>0){
			return (page-1)*pageSize;	
		}
		return 0;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
