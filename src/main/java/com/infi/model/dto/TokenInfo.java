package com.infi.model.dto;

import java.util.List;

import com.infi.utility.StringDigestUtil;

public class TokenInfo extends OpenInfo {
	public TokenInfo() {
		super("", "");
	}

	private static String rootRoleStr = "super";

	public static boolean isSuperRole(String roleid) {
		if (roleid == null || roleid == "") {
			return false;
		}
		return rootRoleStr.equals(roleid);
	}

	public TokenInfo(String name, String role) {
		super(name, role);
		// this.roleid = roleid;
	}

	private String roleid;

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	private List<String> rightStrs;

	public List<String> getRightStrs() {
		return rightStrs;
	}

	public void setRightStrs(List<String> rightStrs) {
		this.rightStrs = rightStrs;
	}
}
