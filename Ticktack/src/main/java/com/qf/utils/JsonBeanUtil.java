package com.qf.utils;

import com.qf.vo.JsonBean;

public class JsonBeanUtil {

	public static JsonBean createJsonBean(int code, Object msg){
		JsonBean bean = new JsonBean();
		bean.setCode(code);
		bean.setMsg(msg);
		return bean;
	}
	
}
