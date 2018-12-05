package com.infi.controller;

import java.util.ArrayList;
import java.util.Base64;
import javax.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.infi.dbentity.qifei.Sysaccount;
import com.infi.model.dto.CurrentUser;
import com.infi.model.dto.OpenInfo;
import com.infi.model.dto.ResponseDto;
import com.infi.model.dto.TokenInfo;
import com.infi.service.IAuthenticationService;
import com.infi.utility.AuthUtils;
import com.infi.utility.Json;

@Controller
public class HomeController {
//	private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	IAuthenticationService service;

	@SuppressWarnings("rawtypes")
	@PostMapping("/api/restlogin")
	@ResponseBody
	public ResponseDto restlogin(@RequestBody Sysaccount account, HttpServletResponse response) throws Exception {
		if (service.login(account)) {
			CurrentUser cuser = new CurrentUser();
			OpenInfo openInfo = new OpenInfo(account.getUsername(), "");
			cuser.setOpenInfo(Base64.getEncoder().encodeToString(Json.serialize(openInfo).getBytes()));

			TokenInfo tokenInfo = new TokenInfo(account.getUsername(), "");
			ArrayList<String> rights = new ArrayList<String>();
			rights.add("");// 暂时用这个
			tokenInfo.setRights(rights);
			cuser.setToken(AuthUtils.buildTokenString(tokenInfo));
//			logger.info(account.getUsername() + " 登录成功");
			return ResponseDto.DataSuccess(cuser);
		} else {
			return ResponseDto.Error("账号或密码错误");
		}
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
	// @GetMapping("/")
	// public String index(HttpServletResponse response) {
	// logger.info("经过index页面的方法");
	// return "index";
	// }
	//
	// // 这种方式返回页面，还需要在pom.xm里手动添加thymeleaf的Starter。
	// // 加完starter后，在application.properties里不需要额外的配置，默认就会去tmplates文件夹下读取模版
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
