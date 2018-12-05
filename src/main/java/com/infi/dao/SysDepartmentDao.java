package com.infi.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.infi.dbentity.qifei.Sysdepartment;
import com.infi.exception.DuplicateEntityException;
import com.infi.exception.ExistsChildException;
import com.infi.model.dto.ListDto;

@Service
public class SysDepartmentDao extends IBasicCrud<Sysdepartment> {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	public JdbcTemplate jdbcTemplate;

	@Override
	public Sysdepartment getById(int id) {
		return (Sysdepartment) jdbcTemplate.query("select * from sysdepartment where  id=? limit 0 , 1 ",
				new Object[] { id }, new BeanPropertyRowMapper<Sysdepartment>(Sysdepartment.class));
	}

	@Override
	public boolean insert(Sysdepartment a) throws DuplicateEntityException {
		List<String> existedNames = (List<String>) jdbcTemplate.query(
				"select name from sysdepartment where name=? limit 0, 1;", new Object[] { a.getName() },
				new BeanPropertyRowMapper<String>(String.class));

		if (existedNames.size() > 0) {
			throw new DuplicateEntityException("组织名已被占用");
		}
		int successCount = jdbcTemplate.update(
				"insert into sysdepartment(name, created, enabled, parentid) values(?, ?, ?, ?) ;",
				new Object[] { a.getName(), new Date(), a.getEnabled(), a.getParentid() });
		return successCount > 0;
	}

	@Override
	public boolean update(Sysdepartment a) throws DuplicateEntityException {
		List<String> existedNames = (List<String>) jdbcTemplate.query(
				"select name from sysdepartment where id!=? and name=? limit 0, 1;",
				new Object[] { a.getId(), a.getName() }, new BeanPropertyRowMapper<String>(String.class));

		if (existedNames.size() > 0) {
			throw new DuplicateEntityException("组织名已被占用");
		}
		int successCount = jdbcTemplate.update(
				"update sysdepartment set name=?,  enabled=? where id=? ;",
				new Object[] { a.getName(), a.getEnabled(), a.getId() });
		return successCount > 0;
	}

	@Override
	public boolean delete(int id) throws ExistsChildException {
		Integer childCount = (Integer) jdbcTemplate.queryForObject(
				"select count(1) from sysdepartment where parentid=?;", new Object[] { id }, Integer.class);
		if (childCount > 0) {
			throw new ExistsChildException("当前组织存在下级组织，不可删除"); 
		}

		int successCount = jdbcTemplate.update("delete from sysdepartment where id=? ;", new Object[] { id });
		return successCount > 0;
	}

	@Override
	public boolean updateStatus(String ids, boolean enabled) {
		int successCount = jdbcTemplate.update("update sysdepartment set enabled=? where id in (" + ids + ");",
				new Object[] { enabled });
		return successCount > 0;
	}

	@Override
	public ListDto<Sysdepartment> getList(Integer page, Integer pageSize) {
		if (page == null || page <= 0) {
			page = 1;
		}
		if (pageSize == null || pageSize <= 0) {
			pageSize = 10;
		}
		String where = "";
		String sql = "select * from sysdepartment " + where + " order by created desc limit ? , ? ";
		Object[] sqlParam = new Object[] { (page - 1) * pageSize, pageSize };

		String countSql = "select count(1) from sysaccount " + where;
		Object[] countSqlParam = new Object[] {};

		ListDto<Sysdepartment> result = new ListDto<>();
		result.setRows((List<Sysdepartment>) jdbcTemplate.query(sql, sqlParam,
				new BeanPropertyRowMapper<Sysdepartment>(Sysdepartment.class)));
		result.setPage(page);
		result.setPageSize(pageSize);
		result.setTotal(jdbcTemplate.queryForObject(countSql, countSqlParam, Integer.class));

		return result;
	}
}
