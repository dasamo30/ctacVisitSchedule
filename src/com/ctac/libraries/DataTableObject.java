package com.ctac.libraries;

public class DataTableObject {
	int iTotalRecords;
	int iTotalDisplayRecords;
	String sEcho;
	String sColumns;
	private Object aaData;
	int status;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getiTotalRecords() {
		return this.iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return this.iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return this.sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return this.sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public Object getAaData() {
		return this.aaData;
	}

	public void setAaData(Object aaData) {
		this.aaData = aaData;
	}
}