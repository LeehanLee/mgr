package com.infi.utility;

import java.nio.charset.Charset;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.infi.model.dto.TokenInfo;

public class AuthUtils {

	private final static Logger logger = LoggerFactory.getLogger(AuthUtils.class);

	public static TokenInfo getTokenInfoFromRequest(HttpServletRequest request) throws Exception {
		if (request == null) {
			return null;
		}
		String tokenStr = request.getHeader("token");
		if (tokenStr == null) {
			return null;
		}
		return buildTokenObj(tokenStr);
	}

	public static String buildTokenString(TokenInfo tokenInfo) {
		String cuserBase64 = Base64.getEncoder().encodeToString(Json.serialize(tokenInfo).getBytes());

		String des = Des.encrypt(cuserBase64, Charset.forName("utf-8"));
		return des + "." + StringDigestUtil.mixedDigest(des);
	}

	public static TokenInfo buildTokenObj(String tokenStr) throws Exception {
		if (tokenStr == null) {
			return null;
		}
		String[] splitedStrs = tokenStr.split("\\.");
		if (splitedStrs.length != 4) {
			throw new Exception("数据段长度验证失败");// 期望的是有4段数据，用.分隔的
		}
		String des = splitedStrs[0];
		try {
			if (tokenStr.replace(des + ".", "").equals(StringDigestUtil.mixedDigest(des))) {
				String s = new String(Base64.getDecoder().decode(Des.decrypt(des, Charset.forName("utf-8"))));
				TokenInfo token = Json.deserialize(s, TokenInfo.class);
				return token;
			} else {
				throw new Exception("权限验证失败");// 表示被篡改了
			}
		} catch (Exception e) {
			logger.error("buildTokenObj faile", e);
			return null;
		}
	}

}
