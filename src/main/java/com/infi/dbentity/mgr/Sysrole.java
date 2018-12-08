package com.infi.dbentity.mgr;
import java.util.Date;

/**
 * sysrole  generated at 2018-12-07 23:50:03 by: eric
 */

public class Sysrole{
	private String id;
	private String name;
	private Date created;
	private boolean enabled;
	private String rights;

	public void setId(String id){
		this.id=id;
	}

	public String getId(){
		return id;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
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

	public void setRights(String rights){
		this.rights=rights;
	}

	public String getRights(){
		return rights;
	}

}
