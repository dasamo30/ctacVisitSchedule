package com.ctac.bean;

public class MenuPerfil implements Comparable<MenuPerfil> {
	private int idPerfil;
	private int idMenu;
	private int padre;
	private int nivel;
	private String nombreMenu;
	private String url;
	private Integer estado;
	private int nroh;
	private String icono;
	private int orden;

	public void setNroh(int nroh) {
		this.nroh = nroh;
	}

	public int getNroh() {
		return this.nroh;
	}

	public int getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public int getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public int getPadre() {
		return this.padre;
	}

	public void setPadre(int padre) {
		this.padre = padre;
	}

	public int getNivel() {
		return this.nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getNombreMenu() {
		return this.nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getIcono() {
		return this.icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String toString() {
		return "MenuPerfil{idPerfil=" + this.idPerfil + ", idMenu=" + this.idMenu + ", padre=" + this.padre + ", nivel="
				+ this.nivel + ", nombreMenu=" + this.nombreMenu + ", url=" + this.url + ", estado=" + this.estado
				+ ", nroh=" + this.nroh + ", icono=" + this.icono + ", orden=" + this.orden + '}';
	}

	public int compareTo(MenuPerfil o) {
		return this.getOrden() - o.getOrden();
	}
}