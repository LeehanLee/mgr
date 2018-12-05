package com.infi.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.infi.dbentity.qifei.Sysaccount;
import com.infi.exception.DuplicateEntityException;
import com.infi.model.dto.ListDto;

@Service
public class SysAccountDao extends IBasicCrud<Sysaccount> {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	public JdbcTemplate jdbcTemplate;

	@Override
	public Sysaccount getById(int id) {
		return (Sysaccount) jdbcTemplate.queryForObject("select * from sysaccount where  id=? limit 0 , 1 ",
				new Object[] { id }, new BeanPropertyRowMapper<Sysaccount>(Sysaccount.class));
	}

	@Override
	public boolean insert(Sysaccount a) throws DuplicateEntityException {
		List<String> existedUsernames = (List<String>) jdbcTemplate.query(
				"select username from sysaccount where username=? limit 0, 1;", new Object[] { a.getUsername() },
				new BeanPropertyRowMapper<String>(String.class));

		if (existedUsernames.size() > 0) {
			throw new DuplicateEntityException("用户名已被占用");
		}

		int successCount = jdbcTemplate.update(
				"insert into sysaccount(username, password, mobile, created, enabled, departmentid, roleid) values(?, ?, ?, ?, ?, ?, ?) ;",
				new Object[] { a.getUsername(), a.getPassword(), a.getMobile(), new Date(), a.getEnabled(),
						a.getDepartmentid(), a.getRoleid() });
		return successCount > 0;
	}

	@Override
	public boolean update(Sysaccount a) throws Exception {
		List<String> existedUsernames = (List<String>) jdbcTemplate.query(
				"select username from sysaccount where id!=? and username=? limit 0, 1;",
				new Object[] { a.getId(), a.getUsername() }, new BeanPropertyRowMapper<String>(String.class));

		if (existedUsernames.size() > 0) {
			throw new Exception("新修改成的用户名已被占用");
		}

		int successCount = jdbcTemplate.update(
				"update sysaccount set username=?, password=?, mobile=?, enabled=?, departmentid=?, roleid=? where id=? ;",
				new Object[] { a.getUsername(), a.getPassword(), a.getMobile(), a.getEnabled(), a.getDepartmentid(),
						a.getRoleid(), a.getId() });
		return successCount > 0;
	}

	@Override
	public boolean delete(int id) {
		int successCount = jdbcTemplate.update("delete from sysaccount where id=? ;", new Object[] { id });
		return successCount > 0;
	}

	@Override
	public boolean updateStatus(String ids, boolean enabled) {
		int successCount = jdbcTemplate.update("update sysaccount set enabled=? where id in (" + ids + ");",
				new Object[] { enabled });
		return successCount > 0;
	}

	@Override
	public ListDto<Sysaccount> getList(Integer page, Integer pageSize) {
		if (page == null || page <= 0) {
			page = 1;
		}
		if (pageSize == null || pageSize <= 0) {
			pageSize = 10;
		}
		String where = "";
		String sql = "select * from sysaccount " + where + " order by created desc limit ? , ? ";
		Object[] sqlParam = new Object[] { (page - 1) * pageSize, pageSize };

		String countSql = "select count(1) from sysaccount " + where;
		Object[] countSqlParam = new Object[] {};

		ListDto<Sysaccount> result = new ListDto<>();
		result.setRows((List<Sysaccount>) jdbcTemplate.query(sql, sqlParam,
				new BeanPropertyRowMapper<Sysaccount>(Sysaccount.class)));
		result.setPage(page);
		result.setPageSize(pageSize);
		result.setTotal(jdbcTemplate.queryForObject(countSql, countSqlParam, Integer.class));

		return result;
	}

	public Sysaccount getByName(String username) {
		List<Sysaccount> result = (List<Sysaccount>) jdbcTemplate.query(
				"select * from sysaccount where  username=? limit 0 , 1 ", new Object[] { username },
				new BeanPropertyRowMapper<Sysaccount>(Sysaccount.class));
		return result.size() > 0 ? result.get(0) : null;

	}
}
