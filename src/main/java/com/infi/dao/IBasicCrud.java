package com.infi.dao;

import java.util.HashMap;

import com.infi.exception.DuplicateEntityException;
import com.infi.exception.ExistsChildException;
import com.infi.model.dto.ListDto;

public abstract class IBasicCrud<T> {

	public abstract boolean insert(T a) throws DuplicateEntityException;

	public abstract boolean update(T a) throws Exception;

	public abstract boolean delete(int id) throws ExistsChildException;

	public abstract boolean updateStatus(String ids, boolean enabled);

	public abstract T getById(Object id);

	public abstract ListDto<T> getList(Integer page, Integer pageSize, HashMap<String, Object> condition);
}
