package com.infi.model.dto.output;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.infi.dbentity.mgr.Sysaccount;
import com.infi.utility.Json;

/**
 * SysaccontOutput
 */
public class SysaccountOutput {

	private int id;
	private String username;
	private String password;
	private String mobile;
	private Date created;
	private boolean enabled;
	private int orgid;
	private String roleid;
	private String roleName;
	private String orgName;
	public List<String> extraright;

	public SysaccountOutput(Sysaccount acc) {
		this.setId(acc.getId());
		this.setUsername(acc.getUsername());
		this.setPassword(acc.getPassword());
		this.setMobile(acc.getMobile());
		this.setCreated(acc.getCreated());
		this.setEnabled(acc.getEnabled());
		this.setOrgid(acc.getOrgid());
		this.setRoleid(acc.getRoleid());
		if (null != acc.getExtraright() && !"".equals(acc.getExtraright())) {
			this.setExtraright(Json.deserialize(acc.getExtraright(), new TypeReference<List<String>>() {
			}));
		}
	}

	public Sysaccount toSysaccount(SysaccountOutput acc) {
		Sysaccount result = new Sysaccount();
		result.setId(acc.getId());
		result.setUsername(acc.getUsername());
		result.setPassword(acc.getPassword());
		result.setMobile(acc.getMobile());
		result.setCreated(acc.getCreated());
		result.setEnabled(acc.isEnabled());
		result.setOrgid(acc.getOrgid());
		result.setRoleid(acc.getRoleid());
		result.setExtraright(Json.serialize((acc.getExtraright())));
		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getOrgid() {
		return orgid;
	}

	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<String> getExtraright() {
		return extraright;
	}

	public void setExtraright(List<String> extraright) {
		this.extraright = extraright;
	}

}