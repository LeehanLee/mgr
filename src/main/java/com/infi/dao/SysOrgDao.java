package com.infi.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.infi.dbentity.mgr.Sysorg;
import com.infi.exception.DuplicateEntityException;
import com.infi.exception.ExistsChildException;
import com.infi.model.dto.ListDto;

@Service
public class SysOrgDao extends IBasicCrud<Sysorg> {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	public JdbcTemplate jdbcTemplate;

	@Override
	public Sysorg getById(Object id) {
		return (Sysorg) jdbcTemplate.query("select * from sysorg where  id=? limit 0 , 1 ", new Object[] { id },
				new BeanPropertyRowMapper<Sysorg>(Sysorg.class));
	}

	@Override
	public boolean insert(Sysorg a) throws DuplicateEntityException {
		List<String> existedNames = (List<String>) jdbcTemplate.query(
				"select name from sysorg where name=? limit 0, 1;", new Object[] { a.getName() },
				new BeanPropertyRowMapper<String>(String.class));

		if (existedNames.size() > 0) {
			throw new DuplicateEntityException("组织名已被占用");
		}
		int successCount = jdbcTemplate.update(
				"insert into sysorg(name, created, enabled, parentid) values(?, ?, ?, ?) ;",
				new Object[] { a.getName(), new Date(), a.getEnabled(), a.getParentid() });
		return successCount > 0;
	}

	@Override
	public boolean update(Sysorg a) throws DuplicateEntityException {
		List<String> existedNames = (List<String>) jdbcTemplate.query(
				"select name from sysorg where id!=? and name=? limit 0, 1;", new Object[] { a.getId(), a.getName() },
				new BeanPropertyRowMapper<String>(String.class));

		if (existedNames.size() > 0) {
			throw new DuplicateEntityException("组织名已被占用");
		}
		int successCount = jdbcTemplate.update("update sysorg set name=?,  enabled=? where id=? ;",
				new Object[] { a.getName(), a.getEnabled(), a.getId() });
		return successCount > 0;
	}

	@Override
	public boolean delete(Object id) throws ExistsChildException {
		Integer childCount = (Integer) jdbcTemplate.queryForObject("select count(1) from sysorg where parentid=?;",
				new Object[] { id }, Integer.class);
		if (childCount > 0) {
			throw new ExistsChildException("当前组织存在下级组织，不可删除");
		}

		int successCount = jdbcTemplate.update("delete from sysorg where id=? ;", new Object[] { id });
		return successCount > 0;
	}

	@Override
	public boolean updateStatus(String ids, boolean enabled) {
		int successCount = jdbcTemplate.update("update sysorg set enabled=? where id in (" + ids + ");",
				new Object[] { enabled });
		return successCount > 0;
	}

	@Override
	public ListDto<Sysorg> getList(Integer page, Integer pageSize, HashMap<String, Object> condition) {
		if (page == null || page <= 0) {
			page = 1;
		}
		if (pageSize == null || pageSize <= 0) {
			pageSize = 10;
		}
		String where = "";
		String sql = "select * from sysorg " + where + " order by created asc limit ? , ? ";
		Object[] sqlParam = new Object[] { (page - 1) * pageSize, pageSize };

		String countSql = "select count(1) from sysorg " + where;
		Object[] countSqlParam = new Object[] {};

		ListDto<Sysorg> result = new ListDto<>();
		result.setRows(
				(List<Sysorg>) jdbcTemplate.query(sql, sqlParam, new BeanPropertyRowMapper<Sysorg>(Sysorg.class)));
		result.setPage(page);
		result.setPageSize(pageSize);
		result.setTotal(jdbcTemplate.queryForObject(countSql, countSqlParam, Integer.class));

		return result;
	}
}
