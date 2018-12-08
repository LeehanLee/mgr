package com.infi.dbentity.mgr;

import java.util.Date;

/**
 * sysright generated at 2018-12-07 23:50:03 by: eric
 */

public class Sysright {
	private String id;
	private String name;
	private Date created;
	private int datatype;
	private String parentid;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}

	public int getDatatype() {
		return datatype;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getParentid() {
		return parentid;
	}

}
