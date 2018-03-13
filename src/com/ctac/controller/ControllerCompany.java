package com.ctac.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.ctac.bean.CompanyBean;
import com.ctac.bean.PerfilBean;
import com.ctac.libraries.DataTableObject;
import com.ctac.service.ServiceVisit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@Controller
@RequestMapping({"/visit"})
public class ControllerCompany {
	
	private ServiceVisit serviceVisit= new ServiceVisit();
	
	@RequestMapping({"/company"})
	public ModelAndView WiewPanelCompany(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String namemenu = "company";
		String titlemenu = "init";
		HashMap route = new HashMap();
		route.put(Integer.valueOf(1), "Visits");
		route.put(Integer.valueOf(2), "company");
		String opmnu = "#lim_4:#lim_9";
		ModelAndView mv = new ModelAndView();
		mv.addObject("menus", sesion.getAttribute("lmemus"));
		//mv.addObject("foto", sesion.getAttribute("foto"));
		mv.addObject("namemenu", namemenu);
		mv.addObject("titlemenu", titlemenu);
		mv.addObject("funtion",
				"<script src=\"" + request.getContextPath() + "/js/visits.js\" type=\"text/javascript\" ></script>");
		mv.addObject("route", route);
		mv.addObject("opmnu", opmnu);
		mv.setViewName("view_panel_company");
		return mv;
	}
	
	@RequestMapping(value = {"/company/ActViewCompany"}, method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView ActViewUsuario() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("view_new_company");
		mav.addObject("formCompany", "formRegisterCompany");
		return mav;
	}
	
	@RequestMapping(value = {"/company/ActRegisterCompany"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActRegisterCompany(@RequestBody CompanyBean companyBean) {
		
		//SimpleDateFormat date=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date =Calendar.getInstance().getTime();
		
		companyBean.setStatus((short) 1);
		companyBean.setRegistration_date(date);
		System.out.println(companyBean.toString());
		int rpta =this.serviceVisit.insertIntoCompany(companyBean);
		return rpta;
	}
	
	
	@RequestMapping(value = {"/company/ActlistCompany"}, method = {RequestMethod.POST})
	@ResponseBody
	public String ActlistCompany(HttpServletRequest request, HttpServletResponse response) {
		String baseurl = request.getContextPath();
		DataTableObject dataTableObject = new DataTableObject();
		ArrayList<CompanyBean> listCompany = this.serviceVisit.selectCompanyBean();
		
		dataTableObject.setAaData(listCompany);
		dataTableObject.setiTotalDisplayRecords(listCompany.size());
		dataTableObject.setiTotalRecords(listCompany.size());
		Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
		String json = gson.toJson(dataTableObject);
		
		System.out.println(json);
		return json;
	}
	
	@RequestMapping(value = {"/company/ActViewModifCompany"}, method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView ActViewModifCompany(
			@RequestParam("id_company") int id_company,
			@RequestParam("company_name") String company_name,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		CompanyBean c = new CompanyBean();
		c.setId_company(id_company);
		c.setCompany_name(company_name);
		mav.setViewName("view_new_company");
		mav.addObject("formCompany", "formModifCompany");
		mav.addObject("CompanyBean", c);
		return mav;
	}
	
	@RequestMapping(value = {"/company/ActModifCompany"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActModifCompany(@RequestBody CompanyBean companyBean) {
		
		//SimpleDateFormat date=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date =Calendar.getInstance().getTime();
		
		//companyBean.setStatus((short) 1);
		//companyBean.setRegistration_date(date);
		System.out.println(companyBean.toString());
		int rpta =this.serviceVisit.updateCompany(companyBean);
		return rpta;
	}
	
	@RequestMapping(value = {"/company/ActDeleteCompany"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActDeleteCompany(@RequestParam("id_company") int id_company) {
		CompanyBean companyBean=new CompanyBean();
		companyBean.setId_company(id_company);
		//companyBean.setStatus((short) 0);
		int rpta = this.serviceVisit.deleteCompany(companyBean);
		return rpta;
	}
	
}
