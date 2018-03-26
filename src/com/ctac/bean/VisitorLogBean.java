package com.ctac.bean;

import java.util.Date;

public class VisitorLogBean {
	
	private int id_visit_schedule;
	private String badge_number;
	private short  type;
	private Date registration_date;
	private short reason;
	public int getId_visit_schedule() {
		return id_visit_schedule;
	}
	public void setId_visit_schedule(int id_visit_schedule) {
		this.id_visit_schedule = id_visit_schedule;
	}
	public String getBadge_number() {
		return badge_number;
	}
	public void setBadge_number(String badge_number) {
		this.badge_number = badge_number;
	}
	public short getType() {
		return type;
	}
	public void setType(short type) {
		this.type = type;
	}
	public Date getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	public short getReason() {
		return reason;
	}
	public void setReason(short reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "VisitorLogBean [id_visit_schedule=" + id_visit_schedule + ", badge_number=" + badge_number + ", type="
				+ type + ", registration_date=" + registration_date + ", reason=" + reason + "]";
	}
	
	
}
