package com.ctac.bean;

public class AjaxResponseBE {
	private String state;
	private String message;
	private String description;
	private Object data;

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getData() {
		return this.data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toString() {
		return "AjaxResponseBE{state=" + this.state + ", message=" + this.message + ", description=" + this.description
				+ ", data=" + this.data + '}';
	}
}