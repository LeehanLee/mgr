package com.infi.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringDigestUtil {
	private final static Logger logger = LoggerFactory.getLogger(StringDigestUtil.class);

	public static void test() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			System.out.println(mixedDigest("this is time " + i + " test"));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
	}

	public static String mixedDigest(String sourceStr) {
		if (sourceStr == null || sourceStr == "") {
			return null;
		}

		byte[] srcBytes = sourceStr.getBytes();
		byte[] shaBytes = null;
		byte[] md5Bytes = null;
		try {
			shaBytes = doSha256(srcBytes);
			String s1 = getBase64String(shaBytes);

			md5Bytes = doMd5(srcBytes);
			String s2 = getBase64String(md5Bytes);
			// byte[] b = concat(shaBytes, md5Bytes);

			byte[] rBytes = doSha256((s1 + "-|-" + s2).getBytes());
			String s3 = getBase64String(rBytes);
			return s1 + "." + s2 + "." + s3;
			// return getBase64String(rBytes);
		} catch (NoSuchAlgorithmException e) {
			logger.error("Encryptor error: " + System.lineSeparator(), e);
		}
		return null;
	}

	public static boolean verify(String sourceStr, String encrypted) {
		return mixedDigest(sourceStr).equals(encrypted);
	}

	public static String md5(String sourceStr) {
		if (sourceStr == null) {
			return null;
		}

		try {
			return bytesToString(doMd5(sourceStr.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			logger.error("Md5 error: " + System.lineSeparator(), e);
		}

		return null;
	}

	private static byte[] doSha256(byte[] srcBytes) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] r = md.digest(srcBytes);
		return r;
	}

	private static byte[] doMd5(byte[] srcBytes) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] r = md.digest(srcBytes);
		return r;
	}

	private static String getBase64String(byte[] srcBytes) {
		return new String(Base64.getEncoder().encode(srcBytes));
	}

	private static String bytesToString(byte[] b) {
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}

	// private static byte[] concat(byte[] arr1, byte[] arr2) {
	// int len1 = arr1.length;
	// int len2 = arr2.length;
	// byte[] result = Arrays.copyOf(arr1, len1 + len2);// 数组扩容
	// System.arraycopy(arr2, 0, result, len1, len2);
	// return result;
	// }
}
