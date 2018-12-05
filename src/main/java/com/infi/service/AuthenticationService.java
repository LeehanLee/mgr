package com.infi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infi.dao.SysAccountDao;
import com.infi.dbentity.qifei.Sysaccount;

@Service
public class AuthenticationService implements IAuthenticationService {
	@Autowired
	SysAccountDao dao;

	@Override
	public boolean login(Sysaccount account) {
		Sysaccount acc = dao.getByName(account.getUsername());
		return acc != null && acc.getPassword().equals(account.getPassword());
	}

}
