package com.darlen.util;

/**
 * 这是一个常用工具类  包括一些字符串转换
 * User: darlenliu
 * Date: 14-6-18
 * Time: 下午4:34
 */
public class Util {

	public static String nullToString(Object inString) {
		return (inString == null ||  "null".equalsIgnoreCase(inString.toString().trim())) ? "" : inString.toString();
	}
}
