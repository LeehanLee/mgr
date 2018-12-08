package com.infi.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.infi.dbentity.mgr.Sysrole;
import com.infi.exception.DuplicateEntityException;
import com.infi.model.dto.ListDto;

@Service
public class SysRoleDao extends IBasicCrud<Sysrole> {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	public JdbcTemplate jdbcTemplate;

	@Override
	public boolean insert(Sysrole a) throws DuplicateEntityException {
		List<String> existedNames = (List<String>) jdbcTemplate.query(
				"select name from sysrole where name=? limit 0, 1;", new Object[] { a.getName() },
				new BeanPropertyRowMapper<String>(String.class));

		if (existedNames.size() > 0) {
			throw new DuplicateEntityException("角色名已被占用");
		}

		int successCount = jdbcTemplate.update(
				"insert into sysrole(id, name, created, enabled, rights) values(?, ?, ?, ?, ?) ;",
				new Object[] { UUID.randomUUID().toString(), a.getName(), new Date(), a.getEnabled(), a.getRights() });
		return successCount > 0;
	}

	@Override
	public boolean update(Sysrole a) throws Exception {
		List<String> existedNames = (List<String>) jdbcTemplate.query(
				"select name from sysrole where id!=? and name=? limit 0, 1;", new Object[] { a.getId(), a.getName() },
				new BeanPropertyRowMapper<String>(String.class));

		if (existedNames.size() > 0) {
			throw new DuplicateEntityException("角色名已被占用");
		}

		int successCount = jdbcTemplate.update("update sysrole set name=?, enabled=? where id=? ;",
				new Object[] { a.getName(), a.getEnabled(), a.getId() });

		return successCount > 0;
	}

	@Override
	public boolean delete(int id) {
		int successCount = jdbcTemplate.update("delete from sysrole where id=? ;", new Object[] { id });
		return successCount > 0;
	}

	@Override
	public boolean updateStatus(String ids, boolean enabled) {
		int successCount = jdbcTemplate.update("update sysrole set enabled=? where id in (" + ids + ");",
				new Object[] { enabled });
		return successCount > 0;
	}

	@Override
	public Sysrole getById(Object id) {
		return (Sysrole) jdbcTemplate.queryForObject("select * from sysrole where  id=? limit 0 , 1 ",
				new Object[] { id }, new BeanPropertyRowMapper<Sysrole>(Sysrole.class));
	}

	@Override
	public ListDto<Sysrole> getList(Integer page, Integer pageSize, HashMap<String, Object> condition) {
		if (page == null || page <= 0) {
			page = 1;
		}
		if (pageSize == null || pageSize <= 0) {
			pageSize = 10;
		}
		String where = "";
		String sql = "select * from sysrole " + where + " order by created desc limit ? , ? ";
		Object[] sqlParam = new Object[] { (page - 1) * pageSize, pageSize };

		String countSql = "select count(1) from sysrole " + where;
		Object[] countSqlParam = new Object[] {};

		ListDto<Sysrole> result = new ListDto<>();
		result.setRows(
				(List<Sysrole>) jdbcTemplate.query(sql, sqlParam, new BeanPropertyRowMapper<Sysrole>(Sysrole.class)));
		result.setPage(page);
		result.setPageSize(pageSize);
		result.setTotal(jdbcTemplate.queryForObject(countSql, countSqlParam, Integer.class));

		return result;
	}

}
