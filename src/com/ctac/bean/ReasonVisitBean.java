package com.ctac.bean;

import java.util.Date;

public class ReasonVisitBean {
	private int id_reason;
	private String  reasons_name;
	private Boolean status;
	private Date registration_date;
	public int getId_reason() {
		return id_reason;
	}
	public void setId_reason(int id_reason) {
		this.id_reason = id_reason;
	}
	public String getReasons_name() {
		return reasons_name;
	}
	public void setReasons_name(String reasons_name) {
		this.reasons_name = reasons_name;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Date getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	@Override
	public String toString() {
		return "ReasonVisitBean [id_reason=" + id_reason + ", reasons_name=" + reasons_name + ", status=" + status
				+ ", registration_date=" + registration_date + "]";
	}
	
	
}
