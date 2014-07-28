package com.lexindasoft.lexindaframe.util;

public class HttpParam implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String value;

	public HttpParam(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
