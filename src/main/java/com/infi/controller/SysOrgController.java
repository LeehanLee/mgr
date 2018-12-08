package com.infi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infi.annotation.RequireAuth;
import com.infi.dao.IBasicCrud;
import com.infi.dao.SysOrgDao;
import com.infi.dbentity.mgr.Sysorg;

@RequireAuth("org")
@RestController
@RequestMapping("/api/org")
public class SysOrgController extends ABasicCrudController<Sysorg> {

	@Autowired
	SysOrgDao dao;

	@Override
	IBasicCrud<Sysorg> getDao() {
		return dao;
	}
}
