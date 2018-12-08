package com.infi.controller;

import com.infi.annotation.RequireAuth;
import com.infi.dao.IBasicCrud;
import com.infi.dao.SysAccountDao;
import com.infi.model.dto.output.SysaccountOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequireAuth("account")
@RestController
@RequestMapping("/api/account")
public class SysAccountController extends ABasicCrudController<SysaccountOutput> {

	@Autowired
	SysAccountDao dao;

	@Override
	IBasicCrud<SysaccountOutput> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
}
