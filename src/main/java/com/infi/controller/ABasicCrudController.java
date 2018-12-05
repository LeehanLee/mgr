package com.infi.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infi.annotation.RequireAuth;
import com.infi.dao.IBasicCrud;
import com.infi.exception.DuplicateEntityException;
import com.infi.exception.ExistsChildException;
import com.infi.model.dto.ListDto;
import com.infi.model.dto.ResponseDto;

public abstract class ABasicCrudController<T> {

	abstract IBasicCrud<T> getDao();

	@RequireAuth("")
	@RequestMapping("/getList")
	public ResponseDto<ListDto<T>> getList(Integer page, Integer pageSize) {
		return ResponseDto.DataSuccess(getDao().getList(page, pageSize));
	}

	@RequireAuth("")
	@RequestMapping("/getById")
	public ResponseDto<T> getById(int id) {
		return ResponseDto.DataSuccess(getDao().getById(id));
	}

	@SuppressWarnings("rawtypes")
	@RequireAuth("")
	@RequestMapping("/insert")
	public ResponseDto insert(@RequestBody T a) throws DuplicateEntityException {
		return ResponseDto.OperationSuccess(getDao().insert(a));
	}

	@SuppressWarnings("rawtypes")
	@RequireAuth("")
	@RequestMapping("/update")
	public ResponseDto update(@RequestBody T a) throws Exception {
		return ResponseDto.OperationSuccess(getDao().update(a));
	}

	@SuppressWarnings("rawtypes")
	@RequireAuth("")
	@RequestMapping("/delete")
	public ResponseDto delete(int id) throws ExistsChildException {
		return ResponseDto.OperationSuccess(getDao().delete(id));
	}

	@SuppressWarnings("rawtypes")
	@RequireAuth("")
	@RequestMapping("/updateStatus")
	public ResponseDto updateStatus(String ids, boolean enabled) {
		return ResponseDto.OperationSuccess(getDao().updateStatus(ids, enabled));
	}
}
