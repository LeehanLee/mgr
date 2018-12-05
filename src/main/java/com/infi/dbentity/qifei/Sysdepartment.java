package com.infi.dbentity.qifei;
import java.util.Date;

/**
 * sysdepartment  generated at 2018-09-16 18:02:22 by: eric
 */

public class Sysdepartment{
	private int id;
	private String name;
	private Date created;
	private boolean enabled;
	private Integer parentid;

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

	public void setParentid(Integer parentid){
		this.parentid=parentid;
	}

	public Integer getParentid(){
		return parentid;
	}

}
