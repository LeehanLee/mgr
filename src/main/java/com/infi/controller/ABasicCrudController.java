package com.infi.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infi.annotation.RequireAuth;
import com.infi.dao.IBasicCrud;
import com.infi.exception.DuplicateEntityException;
import com.infi.exception.ExistsChildException;
import com.infi.model.dto.ListDto;
import com.infi.model.dto.ResponseDto;

@RequireAuth("account")
public abstract class ABasicCrudController<T> {

	abstract IBasicCrud<T> getDao();

	@RequireAuth("getList")
	@RequestMapping("/getList")
	public ResponseDto<ListDto<T>> getList(Integer page, Integer pageSize, HashMap<String, Object> condition) {
		return ResponseDto.DataSuccess(getDao().getList(page, pageSize, condition));
	}

	@RequireAuth("getById")
	@RequestMapping("/getById")
	public ResponseDto<T> getById(int id) {
		return ResponseDto.DataSuccess(getDao().getById(id));
	}

	@SuppressWarnings("rawtypes")
	@RequireAuth("insert")
	@RequestMapping("/insert")
	public ResponseDto insert(@RequestBody T a) throws DuplicateEntityException {
		return ResponseDto.OperationSuccess(getDao().insert(a));
	}

	@SuppressWarnings("rawtypes")
	@RequireAuth("update")
	@RequestMapping("/update")
	public ResponseDto update(@RequestBody T a) throws Exception {
		return ResponseDto.OperationSuccess(getDao().update(a));
	}

	@SuppressWarnings("rawtypes")
	@RequireAuth("delete")
	@RequestMapping("/delete")
	public ResponseDto delete(int id) throws ExistsChildException {
		return ResponseDto.OperationSuccess(getDao().delete(id));
	}

	@SuppressWarnings("rawtypes")
	@RequireAuth("enable")
	@RequestMapping("/enable")
	public ResponseDto enable(String ids) {
		return ResponseDto.OperationSuccess(getDao().updateStatus(ids, true));
	}

	@SuppressWarnings("rawtypes")
	@RequireAuth("disable")
	@RequestMapping("/disable")
	public ResponseDto disable(String ids) {
		return ResponseDto.OperationSuccess(getDao().updateStatus(ids, false));
	}
}
