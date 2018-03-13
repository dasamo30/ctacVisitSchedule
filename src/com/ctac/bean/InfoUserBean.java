package com.ctac.bean;

import java.util.Date;

public class InfoUserBean {
	private int id_usuario;
	private String login;
	private String clave;
	private int estado;
	private Date fecha_reg;
	private int nro_sesion;
	private int sesion_activa;
	private String nombres;
	private String apellidos;
	private String foto;
	private String genero;
	private String dni;
	private String correo;
	private int id_perfil;
	private String perfil;
	private int pestado;
	private int tiempo_sesion;

	public int getId_usuario() {
		return this.id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecha_reg() {
		return this.fecha_reg;
	}

	public void setFecha_reg(Date fecha_reg) {
		this.fecha_reg = fecha_reg;
	}

	public int getNro_sesion() {
		return this.nro_sesion;
	}

	public void setNro_sesion(int nro_sesion) {
		this.nro_sesion = nro_sesion;
	}

	public int getSesion_activa() {
		return this.sesion_activa;
	}

	public void setSesion_activa(int sesion_activa) {
		this.sesion_activa = sesion_activa;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getId_perfil() {
		return this.id_perfil;
	}

	public void setId_perfil(int id_perfil) {
		this.id_perfil = id_perfil;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public int getPestado() {
		return this.pestado;
	}

	public void setPestado(int pestado) {
		this.pestado = pestado;
	}

	public int getTiempo_sesion() {
		return this.tiempo_sesion;
	}

	public void setTiempo_sesion(int tiempo_sesion) {
		this.tiempo_sesion = tiempo_sesion;
	}

	public String toString() {
		return "InfoUserBean [id_usuario=" + this.id_usuario + ", login=" + this.login + ", clave=" + this.clave
				+ ", estado=" + this.estado + ", fecha_reg=" + this.fecha_reg + ", nro_sesion=" + this.nro_sesion
				+ ", sesion_activa=" + this.sesion_activa + ", nombres=" + this.nombres + ", apellidos="
				+ this.apellidos + ", foto=" + this.foto + ", genero=" + this.genero + ", dni=" + this.dni + ", correo="
				+ this.correo + ", id_perfil=" + this.id_perfil + ", perfil=" + this.perfil + ", pestado="
				+ this.pestado + ", tiempo_sesion=" + this.tiempo_sesion + "]";
	}
}