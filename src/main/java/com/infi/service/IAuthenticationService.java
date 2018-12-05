package com.infi.service;

import com.infi.dbentity.qifei.Sysaccount;

public interface IAuthenticationService {

	boolean login(Sysaccount account) throws Exception;
}
