package com.infi.utility;

import java.nio.charset.Charset;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.infi.model.dto.TokenInfo;

public class AuthUtils {

	private final static Logger logger = LoggerFactory.getLogger(AuthUtils.class);

	public static String buildTokenString(TokenInfo tokenInfo) {
		String cuserBase64 = Base64.getEncoder().encodeToString(Json.serialize(tokenInfo).getBytes());

		String des = Des.encrypt(cuserBase64, Charset.forName("utf-8"));
		return des + "." + StringDigestUtil.mixedDigest(des);
	}

	public static TokenInfo buildTokenObj(String tokenStr) {
		if (tokenStr == null) {
			return null;
		}
		String[] splitedStrs = tokenStr.split("\\.");
		if (splitedStrs.length != 4) {
			return null;
		}
		String des = splitedStrs[0];
		try {
			if (tokenStr.replace(des + ".", "").equals(StringDigestUtil.mixedDigest(des))) {
				String s = new String(Base64.getDecoder().decode(Des.decrypt(des, Charset.forName("utf-8"))));
				TokenInfo token = Json.deserialize(s, TokenInfo.class);
				return token;
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + System.lineSeparator() + e.getStackTrace());
			return null;
		}

		return null;
	}

}
