package com.infi.model.dto.output;

import com.infi.dbentity.mgr.Sysaccount;

/**
 * SysaccontOutput
 */
public class SysaccountOutput extends Sysaccount {

    public String roleName;

    public String orgName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

}