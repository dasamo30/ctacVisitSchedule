package com.ctac.bean;

import java.util.Date;

public class EmployeeBean {
	
	private int id_employee;
	private String full_name;
	private String  idcard;
	private String gender;
	private Date registration_date;
	private short status;
	public int getId_employee() {
		return id_employee;
	}
	public void setId_employee(int id_employee) {
		this.id_employee = id_employee;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "EmployeeBean [id_employee=" + id_employee + ", full_name=" + full_name + ", idcard=" + idcard
				+ ", gender=" + gender + ", registration_date=" + registration_date + ", status=" + status + "]";
	}
	
	
}
