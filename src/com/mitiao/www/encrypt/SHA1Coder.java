package com.mitiao.www.encrypt;

import java.security.MessageDigest;

/**
 * 
 * @author:sekift
 * @time:2016-7-26 下午03:42:24
 * @version:
 */
public class SHA1Coder {

	private static final String ALGORITHM_MD5 = "MD5";

	private static final String ALGORITHM_SHA1 = "SHA1";

	/**
	 * encode By MD5
	 * 
	 * @param str
	 * @return String
	 */
	public static String encodeByMD5(String str) {
		return encode(str, ALGORITHM_MD5);
	}

	/**
	 * encode By Sha1
	 * 
	 * @param str
	 * @return String
	 */
	public static String encodeBySha1(String str) {
		return encode(str, ALGORITHM_SHA1);
	}

	/**
	 * encode string
	 * 
	 * @param algorithm
	 * @param str
	 * @return String
	 */
	private static String encode(String str, String algorithm) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(str.getBytes());
			return parseByte2HexStr(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
}
