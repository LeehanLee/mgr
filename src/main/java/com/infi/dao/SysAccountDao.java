package com.infi.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.infi.exception.DuplicateEntityException;
import com.infi.model.dto.ListDto;
import com.infi.model.dto.output.SysaccountOutput;

@Service
public class SysAccountDao extends IBasicCrud<SysaccountOutput> {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	public JdbcTemplate jdbcTemplate;

	@Override
	public SysaccountOutput getById(Object id) {
		return (SysaccountOutput) jdbcTemplate.queryForObject("select * from sysaccount where  id=? limit 0 , 1 ",
				new Object[] { id }, new BeanPropertyRowMapper<SysaccountOutput>(SysaccountOutput.class));
	}

	@Override
	public boolean insert(SysaccountOutput a) throws DuplicateEntityException {
		List<String> existedUsernames = (List<String>) jdbcTemplate.query(
				"select username from sysaccount where username=? limit 0, 1;", new Object[] { a.getUsername() },
				new BeanPropertyRowMapper<String>(String.class));

		if (existedUsernames.size() > 0) {
			throw new DuplicateEntityException("用户名已被占用");
		}

		int successCount = jdbcTemplate.update(
				"insert into sysaccount(username, password, mobile, created, enabled, departmentid, roleid) values(?, ?, ?, ?, ?, ?, ?) ;",
				new Object[] { a.getUsername(), a.getPassword(), a.getMobile(), new Date(), a.getEnabled(),
						a.getOrgid(), a.getRoleid() });
		return successCount > 0;
	}

	@Override
	public boolean update(SysaccountOutput a) throws Exception {
		List<String> existedUsernames = (List<String>) jdbcTemplate.query(
				"select username from sysaccount where id!=? and username=? limit 0, 1;",
				new Object[] { a.getId(), a.getUsername() }, new BeanPropertyRowMapper<String>(String.class));

		if (existedUsernames.size() > 0) {
			throw new Exception("新修改成的用户名已被占用");
		}

		int successCount = jdbcTemplate.update(
				"update sysaccount set username=?, password=?, mobile=?, enabled=?, departmentid=?, roleid=? where id=? ;",
				new Object[] { a.getUsername(), a.getPassword(), a.getMobile(), a.getEnabled(), a.getOrgid(),
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
	public ListDto<SysaccountOutput> getList(Integer page, Integer pageSize, HashMap<String, Object> condition) {
		if (page == null || page <= 0) {
			page = 1;
		}
		if (pageSize == null || pageSize <= 0) {
			pageSize = 10;
		}
		String where = "";
		String fields = "a.id, a.username, a.password, a.mobile, a.created, a.enabled, a.orgid, a.roleid, a.extraright, o.name as orgName, r.name as roleName";
		String sql = "select " + fields
				+ " from sysaccount a join sysorg o on a.orgid=o.id	join sysrole r on a.roleid=r.id " + where
				+ " order by created desc limit ? , ? ";
		Object[] sqlParam = new Object[] { (page - 1) * pageSize, pageSize };

		String countSql = "select count(1) from sysaccount " + where;
		Object[] countSqlParam = new Object[] {};

		ListDto<SysaccountOutput> result = new ListDto<>();
		result.setRows((List<SysaccountOutput>) jdbcTemplate.query(sql, sqlParam,
				new BeanPropertyRowMapper<SysaccountOutput>(SysaccountOutput.class)));
		result.setPage(page);
		result.setPageSize(pageSize);
		result.setTotal(jdbcTemplate.queryForObject(countSql, countSqlParam, Integer.class));

		return result;
	}

	public SysaccountOutput getByName(String username) {
		List<SysaccountOutput> result = (List<SysaccountOutput>) jdbcTemplate.query(
				"select * from sysaccount where  username=? limit 0 , 1 ", new Object[] { username },
				new BeanPropertyRowMapper<SysaccountOutput>(SysaccountOutput.class));
		return result.size() > 0 ? result.get(0) : null;

	}
}
