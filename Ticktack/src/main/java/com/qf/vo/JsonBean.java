package com.qf.vo;

public class JsonBean {

	// 0操作失败;1操作成功
	private int code;
	private Object msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

}
