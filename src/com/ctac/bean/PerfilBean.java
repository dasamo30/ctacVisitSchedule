package com.ctac.bean;

import java.util.Date;

public class PerfilBean {
	private int id_perfil;
	private String nombre;
	private int estado;
	private Date fecha;
	private int tiempo_sesion;
	private String ico_estado;
	private String ico_editar;
	private String ico_permiso;

	public int getId_perfil() {
		return this.id_perfil;
	}

	public void setId_perfil(int id_perfil) {
		this.id_perfil = id_perfil;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getTiempo_sesion() {
		return this.tiempo_sesion;
	}

	public void setTiempo_sesion(int tiempo_sesion) {
		this.tiempo_sesion = tiempo_sesion;
	}

	public String getIco_editar() {
		return this.ico_editar;
	}

	public void setIco_editar(String ico_editar) {
		this.ico_editar = ico_editar;
	}

	public String getIco_permiso() {
		return this.ico_permiso;
	}

	public void setIco_permiso(String ico_permiso) {
		this.ico_permiso = ico_permiso;
	}

	public String getIco_estado() {
		return this.ico_estado;
	}

	public void setIco_estado(String ico_estado) {
		this.ico_estado = ico_estado;
	}

	public String toString() {
		return "PerfilBean{id_perfil=" + this.id_perfil + ", nombre=" + this.nombre + ", estado=" + this.estado
				+ ", fecha=" + this.fecha + ", tiempo_sesion=" + this.tiempo_sesion + ", ico_estado=" + this.ico_estado
				+ ", ico_editar=" + this.ico_editar + ", ico_permiso=" + this.ico_permiso + '}';
	}
}