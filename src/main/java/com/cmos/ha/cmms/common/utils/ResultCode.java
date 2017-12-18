package com.cmos.ha.cmms.common.utils;

/**
 * @author lixinjie
 * @since 2017-12-16
 */
public enum ResultCode {

	FieldBindingError(40001, "绑定错误"),
	RestSuccess(20001, "成功");
	
	private int code;
	private String desc;
	
	private ResultCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
}
