package com.infi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infi.dao.IBasicCrud;
import com.infi.dao.SysAccountDao;
import com.infi.dbentity.qifei.Sysaccount;

@RestController
@RequestMapping("/api/account")
public class SysAccountController extends ABasicCrudController<Sysaccount> {

	@Autowired
	SysAccountDao dao;

	@Override
	IBasicCrud<Sysaccount> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
}
