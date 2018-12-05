package com.infi.dbentity.qifei;
import java.util.Date;

/**
 * sysright  generated at 2018-09-16 18:02:22 by: eric
 */

public class Sysright{
	private int id;
	private String name;
	private Date created;
	private int datatype;
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

	public void setDatatype(int datatype){
		this.datatype=datatype;
	}

	public int getDatatype(){
		return datatype;
	}

	public void setParentid(int parentid){
		this.parentid=parentid;
	}

	public int getParentid(){
		return parentid;
	}

}
