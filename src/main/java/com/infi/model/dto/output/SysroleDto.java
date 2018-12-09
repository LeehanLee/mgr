package com.infi.model.dto.output;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.infi.dbentity.mgr.Sysrole;
import com.infi.utility.Json;

public class SysroleDto {
	private String id;
	private String name;
	private Date created;
	private boolean enabled;
	private List<String> rights;

	public SysroleDto() {

	}

	public SysroleDto(Sysrole role) {
		this.id = role.getId();
		this.name = role.getName();
		this.created = role.getCreated();
		this.enabled = role.getEnabled();
		if (null != role.getRights() && !"".equals(role.getRights())) {
			this.rights = Json.deserialize(role.getRights(), new TypeReference<List<String>>() {
			});
		}
	}

	public Sysrole toSysrole() {
		Sysrole s = new Sysrole();
		s.setId(this.getId());
		s.setName(this.getName());
		s.setCreated(this.getCreated());
		s.setEnabled(this.isEnabled());
		s.setRights(Json.serialize(this.getRights()));
		return s;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<String> getRights() {
		return rights;
	}

	public void setRights(List<String> rights) {
		this.rights = rights;
	}

}
