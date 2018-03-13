package com.ctac.bean;

import com.ctac.bean.MenuEstado;

public abstract class MenuBean {
	private MenuEstado state;
	protected int id;
	protected String name;
	protected String text;
	protected String href;
	protected String parent_id;

	public MenuEstado getState() {
		return this.state;
	}

	public void setState(MenuEstado state) {
		this.state = state;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getParentId() {
		return this.parent_id;
	}

	public void setParentId(String parent_id) {
		this.parent_id = parent_id;
	}

	public abstract void agregarMenuHijo(MenuBean arg0);

	public abstract boolean tieneMenuHijo();
}