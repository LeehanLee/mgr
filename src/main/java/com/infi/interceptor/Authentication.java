package com.infi.interceptor;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.infi.annotation.RequireAuth;
import com.infi.model.dto.TokenInfo;
import com.infi.utility.AuthUtils;

@Component
public class Authentication extends HandlerInterceptorAdapter {

	// private final static Logger logger =
	// LoggerFactory.getLogger(Authentication.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ArrayList<String> requiredAuthArr = new ArrayList<String>();

		HandlerMethod h = null;
		try {
			h = (HandlerMethod) handler;
		} catch (Exception ex) {
			return true;
		}
		RequireAuth controllerAuth = h.getClass().getAnnotation(RequireAuth.class);
		if (controllerAuth != null) {
			requiredAuthArr.add(controllerAuth.value());
		}

		RequireAuth methodAuth = h.getMethod().getAnnotation(RequireAuth.class);
		if (methodAuth != null) {
			requiredAuthArr.add(methodAuth.value());
		}

		if (requiredAuthArr != null && requiredAuthArr.size() > 0) {
			TokenInfo token = AuthUtils.getTokenInfoFromRequest(request);
			if (token == null) {
				String s = "用户权限验证失败";
				response.setStatus(HttpStatus.FORBIDDEN.value());
				throw new Exception(s);
			}

			request.setAttribute("cuser", token);

			if (TokenInfo.isSuperRole(token.getRoleid())) {
				return true;
			}

			Calendar loginTime = token.getLoginTime();
			loginTime.add(Calendar.HOUR, 6);
			// loginTime.add(Calendar.MINUTE, 1);
			if (loginTime.before(Calendar.getInstance())) {
				response.setStatus(HttpStatus.FORBIDDEN.value());
				throw new Exception("登录已过期, 请重新登录");
			}

			String requiredAuth = String.join("/", requiredAuthArr);
			if (!token.getRightStrs().contains(requiredAuth)) {
				response.setStatus(HttpStatus.FORBIDDEN.value());
				throw new Exception("无权限");
			}
		}

		return true;
	}
}
