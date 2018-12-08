package com.infi;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.infi.model.dto.ResponseDto;
import com.infi.model.dto.TokenInfo;
import com.infi.utility.AuthUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseDto<String> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		ResponseDto<String> result = ResponseDto.Error(e.getMessage());
		TokenInfo token = AuthUtils.getTokenInfoFromRequest(req);
		String msg = "未登录";
		if (token != null) {
			msg = "当前用户: " + token.getName();
		}
		logger.error(msg, e);

		return result;
	}
}
