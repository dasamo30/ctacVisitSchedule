package com.ctac.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ctac.bean.MenuBean;
import com.ctac.bean.MenuCompuestoBean;
import com.ctac.bean.MenuEstado;
import com.ctac.bean.MenuPerfil;
import com.ctac.bean.MenuSimpleBean;
import com.ctac.bean.PerfilBean;
import com.ctac.libraries.DataTableObject;
import com.ctac.service.ServiceAccesos;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/perfiles"})
public class ControladorPerfiles {
	private ServiceAccesos serviceAccesos = new ServiceAccesos();
	private ArrayList<MenuPerfil> listMenu;

	@RequestMapping({"/inicio"})
	public ModelAndView WiewPanelPerfiles(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String namemenu = "Perfiles";
		String titlemenu = "inicio";
		HashMap route = new HashMap();
		route.put(Integer.valueOf(1), "Accesos");
		route.put(Integer.valueOf(2), "Perfiles");
		String opmnu = "#lim_1:#lim_3";
		ModelAndView mv = new ModelAndView();
		mv.addObject("menus", sesion.getAttribute("lmemus"));
		mv.addObject("foto", sesion.getAttribute("foto"));
		mv.addObject("namemenu", namemenu);
		mv.addObject("titlemenu", titlemenu);
		mv.addObject("funtion",
				"<script src=\"" + request.getContextPath() + "/js/accesos.js\" type=\"text/javascript\" ></script>");
		mv.addObject("route", route);
		mv.addObject("opmnu", opmnu);
		mv.setViewName("view_panel_perfiles");
		return mv;
	}

	@RequestMapping(value = {"/ActlistaPerfiles"}, method = {RequestMethod.POST})
	@ResponseBody
	public String ActlistaUsuarios(HttpServletRequest request, HttpServletResponse response) {
		String baseurl = request.getContextPath();
		DataTableObject dataTableObject = new DataTableObject();
		ArrayList listPerfiles = this.serviceAccesos.get_list_perfiles();
		Iterator json = listPerfiles.iterator();

		while (json.hasNext()) {
			PerfilBean gson = (PerfilBean) json.next();
			String icono = gson.getEstado() == 1
					? "<span class=\"label label-success\">A</span>"
					: "<span class=\"label label-danger\">D</span>";
			gson.setIco_estado(icono);
			gson.setIco_editar(
					"<button data-toggle=\"modal\" data-target=\"#myModalViewPerfil\" data-remote=\"false\" type=\"button\" data-id=\""
							+ gson.getId_perfil() + "\" id=\"btnViewEditPerfil\" class=\"btn btn-info btn-xs\" href=\""
							+ baseurl
							+ "/perfiles/ActViewModifPerfil\" ><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></button>");
			gson.setIco_permiso(
					"<button data-toggle=\"modal\" data-target=\"#myModalViewPermisos\" data-remote=\"false\" type=\"button\" data-id=\""
							+ gson.getId_perfil()
							+ "\" id=\"btnViewEditPermiso\" class=\"btn btn-warning btn-xs\" href=\"" + baseurl
							+ "/perfiles/ActViewPermisos\" ><i style=\"font-size: 18px;\" class=\"fa fa-lock\"></i></button>");
		}

		dataTableObject.setAaData(listPerfiles);
		dataTableObject.setiTotalDisplayRecords(listPerfiles.size());
		dataTableObject.setiTotalRecords(listPerfiles.size());
		Gson gson1 = (new GsonBuilder()).setPrettyPrinting().create();
		String json1 = gson1.toJson(dataTableObject);
		return json1;
	}

	@RequestMapping(value = {"ActViewNewPerfil"}, method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView ActViewNewPerfil() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("view_new_perfil");
		mav.addObject("formPerfil", "frmrRegisterPerfil");
		return mav;
	}

	@RequestMapping(value = {"ActRegistraPerfil"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActRegistraPerfil(@RequestBody PerfilBean perfilbean) {
		Date fecha = new Date();
		perfilbean.setFecha(fecha);
		perfilbean.setEstado(1);
		perfilbean.setTiempo_sesion(1200);
		return this.serviceAccesos.registraPerfil(perfilbean);
	}

	@RequestMapping(value = {"ActViewModifPerfil"}, method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView ActViewModifPerfil(@RequestParam("idPerfil") int idPerfil, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		PerfilBean p = this.serviceAccesos.get_perfil(idPerfil);
		mav.setViewName("view_new_perfil");
		mav.addObject("formPerfil", "frmModifPerfil");
		mav.addObject("PerfilBean", p);
		return mav;
	}

	@RequestMapping(value = {"ActModificaPerfil"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActModificaPerfil(@RequestBody PerfilBean perfilbean) {
		perfilbean.setTiempo_sesion(1200);
		return this.serviceAccesos.modificarPerfil(perfilbean);
	}

	@RequestMapping(value = {"ActAsignaPermiso"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActAsignaPermiso(@RequestParam("opcion") boolean opcion, @RequestParam("id_perfil") int id_perfil,
			@RequestParam("id_menu") int id_menu) {
		return this.serviceAccesos.asignaPermiso(opcion, id_perfil, id_menu);
	}

	@RequestMapping(value = {"ActViewPermisos"}, method = {RequestMethod.POST})
	@ResponseBody
	public String ActMenusAccesos(@RequestParam("idPerfil") int idPerfil, HttpServletRequest request) {
		Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
		this.listMenu = this.serviceAccesos.get_menus_accesos_perfil(idPerfil);
		ArrayList listaMenus = new ArrayList();
		Iterator arg5 = this.listMenu.iterator();

		while (arg5.hasNext()) {
			MenuPerfil m = (MenuPerfil) arg5.next();
			if (m.getNivel() == 0) {
				Object menu = null;
				if (m.getNroh() > 0) {
					menu = new MenuCompuestoBean();
					((MenuBean) menu).setId(m.getIdMenu());
					((MenuBean) menu).setHref("#");
					((MenuBean) menu).setName(m.getNombreMenu());
					((MenuBean) menu).setParentId("" + m.getNivel());
					((MenuBean) menu).setText(m.getNombreMenu());
					this.llenarMenu((MenuBean) menu);
				} else {
					menu = new MenuSimpleBean();
					((MenuBean) menu).setId(m.getIdMenu());
					((MenuBean) menu).setHref("#");
					((MenuBean) menu).setName(m.getNombreMenu());
					((MenuBean) menu).setParentId("" + m.getNivel());
					((MenuBean) menu).setText(m.getNombreMenu());
				}

				System.out.println(":::::::::::::::" + m.toString());
				boolean cheked = m.getIdPerfil() != 0;
				((MenuBean) menu).setState(new MenuEstado(cheked, false, true, false));
				listaMenus.add(menu);
			}
		}

		return gson.toJson(listaMenus);
	}

	private ArrayList<MenuPerfil> findHijos(int idpadre) {
		ArrayList mp = new ArrayList();
		Iterator arg3 = this.listMenu.iterator();

		while (arg3.hasNext()) {
			MenuPerfil lm = (MenuPerfil) arg3.next();
			if (lm.getPadre() == idpadre) {
				mp.add(lm);
			}
		}

		return mp;
	}

	private void llenarMenu(MenuBean menuPadre) {
		ArrayList llmenu = this.findHijos(menuPadre.getId());
		System.out.println("llenarMenu" + llmenu.size());
		Iterator arg4 = llmenu.iterator();

		while (arg4.hasNext()) {
			MenuPerfil m = (MenuPerfil) arg4.next();
			Object menu;
			if (m.getNroh() > 0) {
				menu = new MenuCompuestoBean();
				((MenuBean) menu).setId(m.getIdMenu());
				((MenuBean) menu).setHref("#");
				((MenuBean) menu).setName(m.getNombreMenu());
				((MenuBean) menu).setParentId("" + m.getNivel());
				((MenuBean) menu).setText(m.getNombreMenu());
				menuPadre.agregarMenuHijo((MenuBean) menu);
				this.llenarMenu((MenuBean) menu);
			} else {
				menu = new MenuSimpleBean();
				((MenuBean) menu).setId(m.getIdMenu());
				((MenuBean) menu).setHref("#");
				((MenuBean) menu).setName(m.getNombreMenu());
				((MenuBean) menu).setParentId("" + m.getNivel());
				((MenuBean) menu).setText(m.getNombreMenu());
				menuPadre.agregarMenuHijo((MenuBean) menu);
			}

			boolean cheked = m.getIdPerfil() != 0;
			((MenuBean) menu).setState(new MenuEstado(cheked, false, true, false));
		}

	}
}