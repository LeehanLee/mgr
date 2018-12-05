package com.infi.dbentity.qifei;
import java.util.Date;

/**
 * sysrole  generated at 2018-09-16 18:02:22 by: eric
 */

public class Sysrole{
	private int id;
	private String name;
	private Date created;
	private boolean enabled;

	public void setId(int id){
		this.id=id;
	}

	public int getId(){
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

}
