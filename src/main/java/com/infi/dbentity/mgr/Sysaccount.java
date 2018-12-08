package com.infi.dbentity.mgr;
import java.util.Date;

/**
 * sysaccount  generated at 2018-12-07 23:50:03 by: eric
 */

public class Sysaccount{
	private int id;
	private String username;
	private String password;
	private String mobile;
	private Date created;
	private boolean enabled;
	private int orgid;
	private String roleid;
	private String extraright;

	public void setId(int id){
		this.id=id;
	}

	public int getId(){
		return id;
	}

	public void setUsername(String username){
		this.username=username;
	}

	public String getUsername(){
		return username;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public String getPassword(){
		return password;
	}

	public void setMobile(String mobile){
		this.mobile=mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setEnabled(boolean enabled){
		this.enabled=enabled;
	}

	public boolean getEnabled(){
		return enabled;
	}

	public void setOrgid(int orgid){
		this.orgid=orgid;
	}

	public int getOrgid(){
		return orgid;
	}

	public void setRoleid(String roleid){
		this.roleid=roleid;
	}

	public String getRoleid(){
		return roleid;
	}

	public void setExtraright(String extraright){
		this.extraright=extraright;
	}

	public String getExtraright(){
		return extraright;
	}

}
