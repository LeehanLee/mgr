package com.infi.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.infi.dbentity.mgr.Sysright;
import com.infi.exception.DuplicateEntityException;
import com.infi.exception.ExistsChildException;
import com.infi.model.dto.ListDto;

@Service
public class SysRightDao extends IBasicCrud<Sysright> {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	public JdbcTemplate jdbcTemplate;

	@Override
	public boolean insert(Sysright a) throws DuplicateEntityException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Sysright a) throws Exception {
		int successCount = jdbcTemplate.update("update sysright set name=? where id=? ;",
				new Object[] { a.getName(), a.getId() });

		return successCount > 0;
	}

	@Override
	public boolean delete(Object id) throws ExistsChildException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStatus(String ids, boolean enabled) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sysright getById(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListDto<Sysright> getList(Integer page, Integer pageSize, HashMap<String, Object> condition) {
		if (page == null || page <= 0) {
			page = 1;
		}
		if (pageSize == null || pageSize <= 0) {
			pageSize = 100000;
		}
		String where = " where id in (null)";// 默认不让查
		List<String> rightids = (List<String>) condition.get("rightids");
		ArrayList<String> newArr = new ArrayList<String>();
		if (condition != null && rightids != null) {
			for (int i = 0; i < rightids.size(); i++) {
				newArr.add("'" + rightids.get(i) + "'");
			}
			where = "where id in (" + String.join(",", newArr) + ")";// 一般用户只查自己权限范围内的
		}
		if (condition.get("roleid") != null && "super".equals(condition.get("roleid"))) {
			where = "";// super角色的用户查可所有
		}
		String fields = "*";
		String sql = "select " + fields + " from sysright " + where + " order by created asc limit ? , ? ";
		Object[] sqlParam = new Object[] { (page - 1) * pageSize, pageSize };

		String countSql = "select count(1) from sysaccount " + where;
		Object[] countSqlParam = new Object[] {};

		ListDto<Sysright> result = new ListDto<>();
		result.setRows((List<Sysright>) jdbcTemplate.query(sql, sqlParam,
				new BeanPropertyRowMapper<Sysright>(Sysright.class)));
		result.setPage(page);
		result.setPageSize(pageSize);
		result.setTotal(jdbcTemplate.queryForObject(countSql, countSqlParam, Integer.class));

		return result;
	}

}
