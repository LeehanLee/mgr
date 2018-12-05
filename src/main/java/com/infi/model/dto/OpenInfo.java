package com.infi.model.dto;

import java.util.Calendar;

public class OpenInfo {
	private String name;
	private String role;
	private Calendar loginTime;

	public OpenInfo(String name, String role) {
		this.name = name;
		this.role = role;
		this.loginTime = Calendar.getInstance();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Calendar getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Calendar loginTime) {
		this.loginTime = loginTime;
	}
}