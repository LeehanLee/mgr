package com.infi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infi.dao.IBasicCrud;
import com.infi.dao.SysDepartmentDao;
import com.infi.dbentity.qifei.Sysdepartment;

@RestController
@RequestMapping("api/org")
public class SysDepartmentController extends ABasicCrudController<Sysdepartment> {

	@Autowired
	SysDepartmentDao dao;

	@Override
	IBasicCrud<Sysdepartment> getDao() {
		return dao;
	}
}
