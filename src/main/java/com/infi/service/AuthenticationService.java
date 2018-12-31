package com.infi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infi.dao.SysAccountDao;
import com.infi.dbentity.mgr.Sysaccount;
import com.infi.exception.NoLogException;

@Service
public class AuthenticationService implements IAuthenticationService {
	@Autowired
	SysAccountDao dao;

	@Override
	public Sysaccount login(Sysaccount account) throws Exception {
		Sysaccount acc = dao.getByName(account.getUsername());
		if (acc != null && !acc.getEnabled() && !acc.getUsername().equals("root")) {// root 账号不受禁用限制
			throw new NoLogException("用户未启用");
		}
		return acc != null && acc.getPassword().equals(account.getPassword()) ? acc : null;
	}

}
