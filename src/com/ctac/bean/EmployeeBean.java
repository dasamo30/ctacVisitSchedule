package com.ctac.bean;

import java.util.Date;

public class EmployeeBean {
	
	private int id_employee;
	private String full_name;
	private String  idcard;
	private int id_occupation;
	private Date registration_date;
	private short status;
	private String occupation_name;
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
	public int getId_occupation() {
		return id_occupation;
	}
	public void setId_occupation(int id_occupation) {
		this.id_occupation = id_occupation;
	}
	public Date getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	public String getOccupation_name() {
		return occupation_name;
	}
	public void setOccupation_name(String occupation_name) {
		this.occupation_name = occupation_name;
	}
	@Override
	public String toString() {
		return "EmployeeBean [id_employee=" + id_employee + ", full_name=" + full_name + ", idcard=" + idcard
				+ ", id_occupation=" + id_occupation + ", registration_date=" + registration_date + ", status=" + status
				+ ", occupation_name=" + occupation_name + "]";
	}
	
}
