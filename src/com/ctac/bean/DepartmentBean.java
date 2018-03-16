package com.ctac.bean;

import java.util.Date;

public class DepartmentBean {
	private int id_department;
	private String department;
	public int getId_department() {
		return id_department;
	}
	public void setId_department(int id_department) {
		this.id_department = id_department;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "DepartmentBean [id_department=" + id_department + ", department=" + department + "]";
	} 
	
	
}
