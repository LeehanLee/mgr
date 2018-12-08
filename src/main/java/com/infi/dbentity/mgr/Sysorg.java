package com.infi.dbentity.mgr;
import java.util.Date;

/**
 * sysorg  generated at 2018-12-07 23:50:03 by: eric
 */

public class Sysorg{
	private int id;
	private String name;
	private Date created;
	private boolean enabled;
	private int parentid;

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

	public void setParentid(int parentid){
		this.parentid=parentid;
	}

	public int getParentid(){
		return parentid;
	}

}
