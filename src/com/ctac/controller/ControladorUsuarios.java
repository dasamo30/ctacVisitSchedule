package com.ctac.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ctac.bean.InfoUserBean;
import com.ctac.bean.TableUsuarioBean;
import com.ctac.libraries.DataTableObject;
import com.ctac.service.ServiceAccesos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping({"/usuarios"})
public class ControladorUsuarios {
	private ServiceAccesos serviceAccesos = new ServiceAccesos();

	@RequestMapping({"/inicio"})
	public ModelAndView WiewPanelUsuarios(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String namemenu = "Usuarios";
		String titlemenu = "inicio";
		HashMap route = new HashMap();
		route.put(Integer.valueOf(1), "Accesos");
		route.put(Integer.valueOf(2), "Usuario");
		String opmnu = "#lim_1:#lim_2";
		ModelAndView mv = new ModelAndView();
		mv.addObject("menus", sesion.getAttribute("lmemus"));
		mv.addObject("foto", sesion.getAttribute("foto"));
		mv.addObject("namemenu", namemenu);
		mv.addObject("titlemenu", titlemenu);
		mv.addObject("funtion",
				"<script src=\"" + request.getContextPath() + "/js/accesos.js\" type=\"text/javascript\" ></script>");
		mv.addObject("route", route);
		mv.addObject("opmnu", opmnu);
		mv.setViewName("view_panel_usuarios");
		return mv;
	}

	@RequestMapping(value = {"/ActlistaUsuarios"}, method = {RequestMethod.POST})
	@ResponseBody
	public String ActlistaUsuarios(HttpServletRequest request, HttpServletResponse response) {
		String baseurl = request.getContextPath();
		ArrayList listUsuarios = this.serviceAccesos.get_list_usuarios();
		Iterator gson = listUsuarios.iterator();

		String json;
		while (gson.hasNext()) {
			TableUsuarioBean dataTableObject = (TableUsuarioBean) gson.next();
			json = dataTableObject.getEstado() == 1
					? "<span class=\"label label-success\">A</span>"
					: "<span class=\"label label-danger\">D</span>";
			dataTableObject.setIco_estado(json);
			dataTableObject.setIco_edit(
					"<button data-toggle=\"modal\" data-target=\"#myModalViewUsuario\" data-remote=\"false\" type=\"button\" data-id=\""
							+ dataTableObject.getId_usuario()
							+ "\" id=\"btnViewEditUsuario\" class=\"btn btn-info btn-xs\" href=\"" + baseurl
							+ "/usuarios/ActViewModifUsuario\" ><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></button>");
			dataTableObject.setIco_delete("<button type=\"button\" data-id=\"" + dataTableObject.getId_usuario()
					+ "\" id=\"btnEliminaUsuario\" class=\"btn btn-danger btn-xs\"  ><i style=\"font-size: 18px;\" class=\"fa fa-trash\"></i></button>");
		}

		DataTableObject dataTableObject1 = new DataTableObject();
		dataTableObject1.setAaData(listUsuarios);
		dataTableObject1.setiTotalDisplayRecords(listUsuarios.size());
		dataTableObject1.setiTotalRecords(listUsuarios.size());
		Gson gson1 = (new GsonBuilder()).setPrettyPrinting().create();
		json = gson1.toJson(dataTableObject1);
		return json;
	}

	@RequestMapping(value = {"ActViewUsuario"}, method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView ActViewUsuario() {
		ArrayList listPerfil = this.serviceAccesos.get_list_perfiles();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("view_nuevo_usuario");
		mav.addObject("listPerfil", listPerfil);
		mav.addObject("formUsuario", "frmrRegistraUsuario");
		return mav;
	}

	@RequestMapping(value = {"ActRegistraUsuario"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActRegistraUsuario(@RequestBody InfoUserBean usuario) {
		int rpta = this.serviceAccesos.registraUsuarios(usuario);

//		try {
//			Thread.sleep(5000L);
//		} catch (InterruptedException arg3) {
//			Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, (String) null, arg3);
//		}

		return rpta;
	}

	@RequestMapping(value = {"ActViewModifUsuario"}, method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView ActViewModifUsuario(@RequestParam("idUsuario") int idUsuario, HttpServletRequest request) {
		ArrayList listPerfil = this.serviceAccesos.get_list_perfiles();
		InfoUserBean usuarioBean = this.serviceAccesos.get_usuario(idUsuario);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("view_nuevo_usuario");
		mav.addObject("listPerfil", listPerfil);
		mav.addObject("usuarioBean", usuarioBean);
		mav.addObject("formUsuario", "frmrModificaUsuario");
		return mav;
	}

	@RequestMapping(value = {"ActModificaUsuario"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActModificaUsuario(@RequestBody InfoUserBean usuario) {
		return this.serviceAccesos.modificarUsuario(usuario);
	}

	@RequestMapping(value = {"ActEliminaUsuario"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActEliminaUsuario(@RequestParam("idUsuario") int idUsuario) {
		int rpta = this.serviceAccesos.eliminaUsuario(idUsuario);
		return rpta;
	}

	@RequestMapping(value = {"ActCambioPassword"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActCambioPassword(@RequestParam("id_usuario") int id_usuario, @RequestParam("passact") String passact,
			@RequestParam("newpass") String newpass) {
		int rpta = this.serviceAccesos.cambioPassword(id_usuario, passact, newpass);
		return rpta;
	}
}