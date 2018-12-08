package com.infi.service;

import com.infi.dbentity.mgr.Sysaccount;

public interface IAuthenticationService {

	Sysaccount login(Sysaccount account) throws Exception;
}
