package com.infi.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infi.dao.SysRightDao;
import com.infi.dao.SysRoleDao;
import com.infi.dbentity.mgr.Sysaccount;
import com.infi.dbentity.mgr.Sysrole;
import com.infi.model.dto.CurrentUser;
import com.infi.model.dto.OpenInfo;
import com.infi.model.dto.ResponseDto;
import com.infi.model.dto.TokenInfo;
import com.infi.service.IAuthenticationService;
import com.infi.utility.AuthUtils;
import com.infi.utility.Json;
import com.infi.utility.StringDigestUtil;

@Controller
public class HomeController {
	// private final static Logger logger =
	// LoggerFactory.getLogger(HomeController.class);

	@Autowired
	IAuthenticationService service;

	@Autowired
	SysRoleDao roledao;

	@Autowired
	SysRightDao rightdao;

	@SuppressWarnings("rawtypes")
	@PostMapping("/api/restlogin")
	@ResponseBody
	public ResponseDto restlogin(@RequestBody Sysaccount account, HttpServletResponse response) throws Exception {
		Sysaccount acc = service.login(account);
		if (acc != null) {
			Sysrole role = roledao.getById(acc.getRoleid());

			CurrentUser cuser = new CurrentUser();
			OpenInfo openInfo = new OpenInfo(acc.getUsername(), role.getName());

			TokenInfo tokenInfo = new TokenInfo(acc.getUsername(), role.getName());
			tokenInfo.setRoleid(role.getId());

			tokenInfo.setRightStrs(mergeRoleRightsAndAccRights(acc, role));
			HashMap<String, Object> con = new HashMap<>();
			con.put("rightids", tokenInfo.getRightStrs());
			con.put("roleid", acc.getRoleid());

			openInfo.setRights(rightdao.getList(1, 100000, con).getRows());
			cuser.setOpenInfo(Json.serialize(openInfo));

			cuser.setToken(AuthUtils.buildTokenString(tokenInfo));

			return ResponseDto.DataSuccess(cuser);
		} else {
			return ResponseDto.Error("账号或密码错误");
		}
	}

	private ArrayList<String> mergeRoleRightsAndAccRights(Sysaccount acc, Sysrole role) throws Exception {
		ArrayList<String> result = new ArrayList<String>();

		if (role == null) {
			throw new Exception("角色不存在");
		}

		if (!role.getEnabled() && role.getId() != "super") {// super 角色不受禁用限制
			throw new Exception("角色未启用");
		}

		String rightsStr = role.getRights();// ("right 1", "right 2", "right 3")
		result.addAll(buildRightListFromStr(rightsStr));// 账号角色所有的权限
		result.addAll(buildRightListFromStr(acc.getExtraright()));// 也可对一个账号额外指定权限

		return result;
	}

	/**
	 * rightsStr is like ("right 1", "right 2", "right 3")
	 */
	private ArrayList<String> buildRightListFromStr(String rightsStr) {
		ArrayList<String> result = new ArrayList<String>();
		if (rightsStr != null && rightsStr != "" && rightsStr.startsWith("(") && rightsStr.endsWith(")")) {
			String rights = rightsStr.substring(1, rightsStr.length() - 1);

			String[] rightArr = rights.split("\\,");
			for (String r : rightArr) {
				if (r != null && r != "" && r.length() > 2 && r.startsWith("\"") && r.endsWith("\"")) {
					// result.add(StringDigestUtil.mixedDigest(r.substring(1, r.length() - 1)));
					result.add(r.substring(1, r.length() - 1));
				}
			}
		}
		return result;
	}

	// @RequestMapping("/encode")
	// public String encode(String data) {
	// return Des.encrypt(data, Charset.forName("utf8"));
	// }
	//
	// @RequestMapping("/decode")
	// public String decode(String data) throws Exception {
	// return Des.decrypt(data, Charset.forName("utf8"));
	// }
	//
	@GetMapping("/")
	public String index(HttpServletResponse response) {
		return "index";
	}
	//
	// // 这种方式返回页面，还需要在pom.xm里手动添加thymeleaf的Starter。
	// // 加完starter后，在application.properties里不需要额外的配置，默认就会去templates文件夹下读取模版
	// @GetMapping("login")
	// public String login(HttpServletResponse response) {
	// logger.info("经过login页面的方法");
	// CookieUtils.writeCookie(response, "username", "ttt");
	// CookieUtils.writeCookie(response, "login_time", new Date().toString());
	// return "home/login";
	// }
	//
	// @PostMapping("login")
	// public String login(String username, String password) {
	// return "登录成功";
	// }
}
