package com.ctac.service;

import com.ctac.bean.InfoUserBean;
import com.ctac.bean.MenuPerfil;
import com.ctac.bean.PerfilBean;
import com.ctac.bean.TableUsuarioBean;
import com.ctac.dao.DAOFactory;
import com.ctac.dao.IAccesosDAO;
import java.util.ArrayList;

public class ServiceAccesos {
	private DAOFactory fabrica = DAOFactory.getDAOFactory(3);
	private IAccesosDAO accesoDao;

	public ServiceAccesos() {
		this.accesoDao = this.fabrica.getAccesosDAO();
	}

	public int ValidaLogin(InfoUserBean usuarioBean) {
		return this.accesoDao.validadUsuario(usuarioBean);
	}

	public InfoUserBean getUserInfo(String usuario) {
		return this.accesoDao.getUserInfo(usuario);
	}

	public ArrayList<MenuPerfil> get_menus_accesos_perfiles() {
		return this.accesoDao.get_menus_accesos_perfiles();
	}

	public ArrayList<MenuPerfil> get_permisos_menus_accesos() {
		return this.accesoDao.get_permisos_menus_accesos();
	}

	public ArrayList<TableUsuarioBean> get_list_usuarios() {
		return this.accesoDao.get_list_usuarios();
	}

	public int registraUsuarios(InfoUserBean usuario) {
		return this.accesoDao.registraUsuarios(usuario);
	}

	public int eliminaUsuario(int idUsuario) {
		return this.accesoDao.eliminaUsuario(idUsuario);
	}

	public int modificarUsuario(InfoUserBean usuario) {
		return this.accesoDao.modificarUsuario(usuario);
	}

	public InfoUserBean get_usuario(int idUsuario) {
		return this.accesoDao.get_usuario(idUsuario);
	}

	public ArrayList<PerfilBean> get_list_perfiles() {
		return this.accesoDao.get_list_perfiles();
	}

	public int registraPerfil(PerfilBean perfil) {
		return this.accesoDao.registraPerfil(perfil);
	}

	public PerfilBean get_perfil(int idPerfil) {
		return this.accesoDao.get_perfil(idPerfil);
	}

	public int modificarPerfil(PerfilBean perfil) {
		return this.accesoDao.modificarPerfil(perfil);
	}

	public ArrayList<MenuPerfil> get_menus_accesos_perfil(int idPerfil) {
		return this.accesoDao.get_menus_accesos_perfil(idPerfil);
	}

	public int cambioPassword(int idUsuario, String passact, String newpass) {
		return this.accesoDao.cambioPassword(idUsuario, passact, newpass);
	}

	public int asignaPermiso(boolean opcion, int id_perfil, int id_menu) {
		return this.accesoDao.asignaPermiso(opcion, id_perfil, id_menu);
	}
}