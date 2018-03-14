package com.ctac.controller;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/panel"})
public class ControladorPrincipal {
	@RequestMapping({"/home"})
	public ModelAndView WiewPanelPrincipal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String namemenu = "Main Panel";
		String titlemenu = "";
		HashMap route = new HashMap();
		route.put(Integer.valueOf(1), "Panel Principal");
		ModelAndView mv = new ModelAndView();
		mv.addObject("menus", sesion.getAttribute("lmemus"));
		mv.addObject("foto", sesion.getAttribute("foto"));
		mv.addObject("namemenu", namemenu);
		mv.addObject("titlemenu", titlemenu);
		mv.addObject("route", route);
		mv.setViewName("view_panel");
		System.out.println("panelllllllll::::::::::::."+sesion.getAttribute("lmemus").toString());
		
		return mv;
	}

	@RequestMapping(value = {"cerrar"}, method = {RequestMethod.GET})
	@ResponseBody
	public void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sesionOk = request.getSession();
		sesionOk.invalidate();
		response.sendRedirect(request.getContextPath() + "/login");
	}
}