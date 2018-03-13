package com.ctac.dao;

import com.ctac.bean.InfoUserBean;
import com.ctac.bean.MenuPerfil;
import com.ctac.bean.PerfilBean;
import com.ctac.bean.TableUsuarioBean;
import java.util.ArrayList;

public interface IAccesosDAO {
	int validadUsuario(InfoUserBean arg0);

	InfoUserBean getUserInfo(String arg0);

	ArrayList<MenuPerfil> get_menus_accesos_perfiles();

	ArrayList<MenuPerfil> get_permisos_menus_accesos();

	ArrayList<MenuPerfil> get_menus_accesos_perfil(int arg0);

	ArrayList<TableUsuarioBean> get_list_usuarios();

	int registraUsuarios(InfoUserBean arg0);

	int eliminaUsuario(int arg0);

	int modificarUsuario(InfoUserBean arg0);

	InfoUserBean get_usuario(int arg0);

	ArrayList<PerfilBean> get_list_perfiles();

	int registraPerfil(PerfilBean arg0);

	PerfilBean get_perfil(int arg0);

	int modificarPerfil(PerfilBean arg0);

	int cambioPassword(int arg0, String arg1, String arg2);

	int asignaPermiso(boolean arg0, int arg1, int arg2);
}