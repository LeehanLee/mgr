package com.infi.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infi.annotation.RequireAuth;
import com.infi.dao.SysRoleDao;
import com.infi.dbentity.mgr.Sysrole;
import com.infi.exception.DuplicateEntityException;
import com.infi.exception.ExistsChildException;
import com.infi.model.dto.ListDto;
import com.infi.model.dto.ResponseDto;
import com.infi.model.dto.output.SysroleDto;

@RequireAuth("role")
@RestController
@RequestMapping("/api/role")
public class SysRoleController {

	@Autowired
	SysRoleDao dao;

	@SuppressWarnings("rawtypes")
	@RequireAuth("insert")
	@RequestMapping("/insert")
	public ResponseDto insert(@RequestBody SysroleDto a) throws DuplicateEntityException {
		return ResponseDto.OperationSuccess(dao.insert(a.toSysrole()));
	}

	@SuppressWarnings("rawtypes")
	@RequireAuth("update")
	@RequestMapping("/update")
	public ResponseDto update(@RequestBody SysroleDto a) throws Exception {
		return ResponseDto.OperationSuccess(dao.update(a.toSysrole()));
	}

	@RequireAuth("getList")
	@RequestMapping("/getList")
	public ResponseDto<ListDto<SysroleDto>> getSysroleList(Integer page, Integer pageSize,
			HashMap<String, Object> condition) {
		ListDto<Sysrole> data = dao.getList(page, pageSize, condition);
		ListDto<SysroleDto> resultData = new ListDto<>();
		ArrayList<SysroleDto> resultList = new ArrayList<SysroleDto>();
		for (Sysrole r : data.getRows()) {
			resultList.add(new SysroleDto(r));
		}
		resultData.setRows(resultList);
		resultData.setPage(data.getPage());
		resultData.setPageSize(data.getPageSize());
		resultData.setTotal(data.getTotal());
		return ResponseDto.DataSuccess(resultData);
	}

	@SuppressWarnings("rawtypes")
	@RequireAuth("delete")
	@RequestMapping("/delete")
	public ResponseDto delete(String id) throws ExistsChildException {
		return ResponseDto.OperationSuccess(dao.delete(id));
	}

	@SuppressWarnings("rawtypes")
	@RequireAuth("enable")
	@RequestMapping("/enable")
	public ResponseDto enable(String ids) {
		return ResponseDto.OperationSuccess(dao.updateStatus(ids, true));
	}

	@SuppressWarnings("rawtypes")
	@RequireAuth("disable")
	@RequestMapping("/disable")
	public ResponseDto disable(String ids) {
		return ResponseDto.OperationSuccess(dao.updateStatus(ids, false));
	}
}
