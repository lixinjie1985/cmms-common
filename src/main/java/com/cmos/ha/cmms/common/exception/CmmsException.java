package com.cmos.ha.cmms.common.exception;

/**
 * @author lixinjie
 * @since 2017-12-16
 */
@SuppressWarnings("serial")
public abstract class CmmsException extends RuntimeException {

	public CmmsException(String message) {
		 super(message);
	}
	
	public CmmsException(String message, Throwable cause) {
		 super(message, cause);
	}
	
	public abstract int getCode();
	
	public abstract String getDesc();
}
