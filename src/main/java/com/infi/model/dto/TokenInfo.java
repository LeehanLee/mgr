package com.infi.model.dto;

import java.util.List;

public class TokenInfo extends OpenInfo {
	public TokenInfo() {
		super("", "");
	}

	public TokenInfo(String name, String role) {
		super(name, role);
	}

	public List<String> rights;

	public List<String> getRights() {
		return rights;
	}

	public void setRights(List<String> rights) {
		this.rights = rights;
	}
}
