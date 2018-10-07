package com.qf.utils;

public class StringUtil {
	
	/**
	 * 判断字符串是否为空
	 * @param info 需要进行判断的字符串
	 * @return 若字符串为空，返回true；否则，反之；
	 */
	public static boolean isEmpty(String info) {
		
		if (info == null || info.trim().equals("")) {
			return true;
		} else {
			return false;
		}
		
	} 
		
}
