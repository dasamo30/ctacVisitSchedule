package com.ctac.bean;

import java.util.Date;

public class OccupationBean {
	private int id_occupation;
	private String occupation;
	public int getId_occupation() {
		return id_occupation;
	}
	public void setId_occupation(int id_occupation) {
		this.id_occupation = id_occupation;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	@Override
	public String toString() {
		return "OccupationBean [id_occupation=" + id_occupation + ", occupation=" + occupation + "]";
	} 
	
	
}
