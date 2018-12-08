package com.infi.model.dto;

import java.util.Calendar;
import java.util.List;

import com.infi.dbentity.mgr.Sysright;

public class OpenInfo {
	private String name;
	private String role;
	private Calendar loginTime;
	private List<Sysright> rights;

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

	public List<Sysright> getRights() {
		return rights;
	}

	public void setRights(List<Sysright> rights) {
		this.rights = rights;
	}

}