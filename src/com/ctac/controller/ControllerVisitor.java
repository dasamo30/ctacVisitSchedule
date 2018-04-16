package com.ctac.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

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

import com.ctac.bean.EmployeeBean;
import com.ctac.bean.VisitorBean;
import com.ctac.libraries.DataTableObject;
import com.ctac.service.ServiceVisit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping({"/visit"})
public class ControllerVisitor {
	private ServiceVisit serviceVisit= new ServiceVisit();
	
	@RequestMapping({"/visitor"})
	public ModelAndView WiewPanelVisitor(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String namemenu = "Visitor";
		String titlemenu = "init";
		HashMap route = new HashMap();
		route.put(Integer.valueOf(1), "Visits");
		route.put(Integer.valueOf(2), "Visitors");
		String opmnu = "#lim_4:#lim_7";
		ModelAndView mv = new ModelAndView();
		mv.addObject("menus", sesion.getAttribute("lmemus"));
		//mv.addObject("foto", sesion.getAttribute("foto"));
		mv.addObject("namemenu", namemenu);
		mv.addObject("titlemenu", titlemenu);
		mv.addObject("funtion",
				"<script src=\"" + request.getContextPath() + "/js/visits.js\" type=\"text/javascript\" ></script>");
		mv.addObject("route", route);
		mv.addObject("opmnu", opmnu);
		mv.setViewName("view_panel_visitor");
		return mv;
	}
	
	
	@RequestMapping(value = {"/visitor/ActViewVisitor"}, method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView ActViewUsuario() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("view_new_visitor");
		mav.addObject("formVisitor", "formRegisterVisitor");
		return mav;
	}
	
	
	@RequestMapping(value = {"/visitor/ActRegisterVisitor"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActRegisterVisitor(@RequestBody VisitorBean visitorBean) {
		
		//SimpleDateFormat date=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date =Calendar.getInstance().getTime();
		
		visitorBean.setStatus((short) 1);
		visitorBean.setRegistration_date(date);
		//System.out.println(visitorBean.toString());
		int rpta =this.serviceVisit.insertIntoVisitor(visitorBean);
		return rpta;
	}
	
	
	@RequestMapping(value = {"/visitor/ActlistVisitor"}, method = {RequestMethod.POST})
	@ResponseBody
	public String ActRegisterVisitor(HttpServletRequest request, HttpServletResponse response) {
		
		DataTableObject dataTableObject = new DataTableObject();
		ArrayList<VisitorBean> listVisitor = this.serviceVisit.selectVisitorBean();
		
		dataTableObject.setAaData(listVisitor);
		dataTableObject.setiTotalDisplayRecords(listVisitor.size());
		dataTableObject.setiTotalRecords(listVisitor.size());
		Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
		String json = gson.toJson(dataTableObject);
		
		//System.out.println(json);
		return json;
	}
	
	
	@RequestMapping(value = {"/visitor/ActViewModifVisitor"}, method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView ActViewModifVisitor(
			@RequestParam("id_visitor") int id_visitor,
			HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		VisitorBean v = serviceVisit.selectVisitorById(id_visitor);
				//new EmployeeBean();
		//e.setId_employee(id_employee);
		
		
		mav.setViewName("view_new_visitor");
		mav.addObject("formVisitor", "formModifVisitor");
		mav.addObject("VisitorBean", v);
		return mav;
	}
	
	
	@RequestMapping(value = {"/visitor/ActModifVisitor"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActModifVisitor(@RequestBody VisitorBean visitorBean) {
		
		//System.out.println(visitorBean.toString());
		int rpta =this.serviceVisit.updateVisitor(visitorBean);
		return rpta;
	}
	
	@RequestMapping(value = {"/visitor/ActDeleteVisitor"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActDeleteVisitor(@RequestParam("id_visitor") int id_visitor) {
		VisitorBean visitorBean=new VisitorBean();
		visitorBean.setId_visitor(id_visitor);
		int rpta = this.serviceVisit.deleteVisitor(visitorBean);
		return rpta;
	}
	 
}
