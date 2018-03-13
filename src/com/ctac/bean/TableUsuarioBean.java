package com.ctac.bean;

import com.ctac.bean.InfoUserBean;

public class TableUsuarioBean extends InfoUserBean {
	private String ico_estado;
	private String ico_edit;
	private String ico_delete;

	public String getIco_estado() {
		return this.ico_estado;
	}

	public void setIco_estado(String ico_estado) {
		this.ico_estado = ico_estado;
	}

	public String getIco_edit() {
		return this.ico_edit;
	}

	public void setIco_edit(String ico_edit) {
		this.ico_edit = ico_edit;
	}

	public String getIco_delete() {
		return this.ico_delete;
	}

	public void setIco_delete(String ico_delete) {
		this.ico_delete = ico_delete;
	}

	public String toString() {
		return super.toString() + "TableUsuarioBean{" + "ico_estado=" + this.ico_estado + ", ico_edit=" + this.ico_edit
				+ ", ico_delete=" + this.ico_delete + '}';
	}
}