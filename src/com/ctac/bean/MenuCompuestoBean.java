package com.ctac.bean;

import com.ctac.bean.MenuBean;
import java.util.ArrayList;

public class MenuCompuestoBean extends MenuBean {
	private ArrayList<MenuBean> nodes = new ArrayList<>();

	public void agregarMenuHijo(MenuBean menu) {
		this.nodes.add(menu);
	}

	public boolean tieneMenuHijo() {
		return this.nodes.size() > 0;
	}
}