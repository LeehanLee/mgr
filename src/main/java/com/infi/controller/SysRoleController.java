package com.infi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infi.annotation.RequireAuth;
import com.infi.dao.IBasicCrud;
import com.infi.dao.SysRoleDao;
import com.infi.dbentity.mgr.Sysrole;

@RequireAuth("role")
@RestController
@RequestMapping("/api/role")
public class SysRoleController extends ABasicCrudController<Sysrole> {

	@Autowired
	SysRoleDao dao;

	@Override
	IBasicCrud<Sysrole> getDao() {
		return dao;
	}
}
