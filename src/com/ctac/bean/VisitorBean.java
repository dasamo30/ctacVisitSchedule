package com.ctac.bean;

import java.util.Date;

public class VisitorBean {
	private int id_visitor;
	private String full_name;
	private String number_license;
	private String  citizen_ship;
	private String  email;
	private String phone_number;
	private Date registration_date;
	private short status;
	public int getId_visitor() {
		return id_visitor;
	}
	public void setId_visitor(int id_visitor) {
		this.id_visitor = id_visitor;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getNumber_license() {
		return number_license;
	}
	public void setNumber_license(String number_license) {
		this.number_license = number_license;
	}
	public String getCitizen_ship() {
		return citizen_ship;
	}
	public void setCitizen_ship(String citizen_ship) {
		this.citizen_ship = citizen_ship;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
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
	@Override
	public String toString() {
		return "VisitorsBean [id_visitor=" + id_visitor + ", full_name=" + full_name + ", number_license="
				+ number_license + ", citizen_ship=" + citizen_ship + ", email=" + email + ", phone_number="
				+ phone_number + ", registration_date=" + registration_date + ", status=" + status + "]";
	}
}
