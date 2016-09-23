package com.excep;
public class BusinessException extends Exception {
	private static final long serialVersionUID = 5185539531941777479L;

	private String code;

	public BusinessException() {
	}

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Throwable fillInStackTrace() {
		return null;
	}
}