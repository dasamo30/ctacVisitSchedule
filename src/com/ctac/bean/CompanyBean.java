package com.ctac.bean;

import java.util.Date;

public class CompanyBean {
	private int id_company; //serial NOT NULL,
	private String company_name; // character varying(100),
	private short status;//smallint,
	private Date registration_date;
	public int getId_company() {
		return id_company;
	}
	public void setId_company(int id_company) {
		this.id_company = id_company;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
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
		return "CompanyBean [id_company=" + id_company + ", company_name=" + company_name + ", status=" + status
				+ ", registration_date=" + registration_date + "]";
	}
	
	
}
