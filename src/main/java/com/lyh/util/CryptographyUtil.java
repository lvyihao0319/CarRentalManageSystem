package com.lyh.util;


import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具
 * @author Administrator
 *
 */
public class CryptographyUtil {
	/**
	 * Md5加密
	 * @param str  加密的内容
	 * @param salt  盐值 
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
}
