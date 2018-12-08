package com.infi.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.infi.annotation.RequireAuth;
import com.infi.dao.IBasicCrud;
import com.infi.dao.SysRightDao;
import com.infi.dbentity.mgr.Sysright;
import com.infi.model.dto.ListDto;
import com.infi.model.dto.ResponseDto;
import com.infi.model.dto.TokenInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequireAuth("right")
@RestController
@RequestMapping("/api/right")
public class SysRightController extends ABasicCrudController<Sysright> {

    @Autowired
    SysRightDao dao;

    @Override
    IBasicCrud<Sysright> getDao() {
        return dao;
    }

    @RequireAuth("getMyRights")
    @RequestMapping("/getMyRights")
    public ResponseDto<ListDto<Sysright>> getMyRights(Integer page, Integer pageSize,
            @RequestAttribute("cuser") TokenInfo cuser) {
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("rightids", cuser.getRightStrs());
        condition.put("roleid", cuser.getRoleid());
        return ResponseDto.DataSuccess(getDao().getList(page, pageSize, condition));
    }
}
