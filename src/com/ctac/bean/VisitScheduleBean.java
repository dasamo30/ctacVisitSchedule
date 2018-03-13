package com.ctac.bean;

import java.util.Date;

public class VisitScheduleBean {
	
	private int id_visit_schedule;
	private Date date_hour;
	private String number_badge;
	private int id_company;
	private int id_employee;
	private int id_visitor;
	private Date registration_date;
	private int id_usuario;
	private int id_reason;
	private String date;
	private String hour;
	private short status;
	private String full_name_visitor;
	private String number_license;
	private String citizen_ship;
	private String email;
	private String phone_number;
	private String full_name_employee;
	private String company_name;
	private String reasons_name;
	public int getId_visit_schedule() {
		return id_visit_schedule;
	}
	public void setId_visit_schedule(int id_visit_schedule) {
		this.id_visit_schedule = id_visit_schedule;
	}
	public Date getDate_hour() {
		return date_hour;
	}
	public void setDate_hour(Date date_hour) {
		this.date_hour = date_hour;
	}
	public String getNumber_badge() {
		return number_badge;
	}
	public void setNumber_badge(String number_badge) {
		this.number_badge = number_badge;
	}
	public int getId_company() {
		return id_company;
	}
	public void setId_company(int id_company) {
		this.id_company = id_company;
	}
	public int getId_employee() {
		return id_employee;
	}
	public void setId_employee(int id_employee) {
		this.id_employee = id_employee;
	}
	public int getId_visitor() {
		return id_visitor;
	}
	public void setId_visitor(int id_visitor) {
		this.id_visitor = id_visitor;
	}
	public Date getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getId_reason() {
		return id_reason;
	}
	public void setId_reason(int id_reason) {
		this.id_reason = id_reason;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	public String getFull_name_visitor() {
		return full_name_visitor;
	}
	public void setFull_name_visitor(String full_name_visitor) {
		this.full_name_visitor = full_name_visitor;
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
	public String getFull_name_employee() {
		return full_name_employee;
	}
	public void setFull_name_employee(String full_name_employee) {
		this.full_name_employee = full_name_employee;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getReasons_name() {
		return reasons_name;
	}
	public void setReasons_name(String reasons_name) {
		this.reasons_name = reasons_name;
	}
	@Override
	public String toString() {
		return "VisitScheduleBean [id_visit_schedule=" + id_visit_schedule + ", date_hour=" + date_hour
				+ ", number_badge=" + number_badge + ", id_company=" + id_company + ", id_employee=" + id_employee
				+ ", id_visitor=" + id_visitor + ", registration_date=" + registration_date + ", id_usuario="
				+ id_usuario + ", id_reason=" + id_reason + ", date=" + date + ", hour=" + hour + ", status=" + status
				+ ", full_name_visitor=" + full_name_visitor + ", number_license=" + number_license + ", citizen_ship="
				+ citizen_ship + ", email=" + email + ", phone_number=" + phone_number + ", full_name_employee="
				+ full_name_employee + ", company_name=" + company_name + ", reasons_name=" + reasons_name + "]";
	}
	
	
}
