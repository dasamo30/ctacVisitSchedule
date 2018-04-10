package com.ctac.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ctac.bean.VisitScheduleBean;
import com.ctac.libraries.DataTableObject;
import com.ctac.service.ServiceVisit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping({"/visit"})
public class ControllerVisitReport {
	private ServiceVisit serviceVisit= new ServiceVisit();
	
	@RequestMapping({"/report"})
	public ModelAndView WiewPanelreport(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String namemenu = "report";
		String titlemenu = "init";
		HashMap route = new HashMap();
		route.put(Integer.valueOf(1), "Visits");
		route.put(Integer.valueOf(2), "report");
		String opmnu = "#lim_4:#lim_6";
		ModelAndView mv = new ModelAndView();
		mv.addObject("menus", sesion.getAttribute("lmemus"));
		//mv.addObject("foto", sesion.getAttribute("foto"));
		mv.addObject("namemenu", namemenu);
		mv.addObject("titlemenu", titlemenu);
		mv.addObject("funtion",
				"<script src=\"" + request.getContextPath() + "/js/visits.js\" type=\"text/javascript\" ></script>");
		mv.addObject("route", route);
		mv.addObject("opmnu", opmnu);
		mv.setViewName("view_panel_report");
		return mv;
	}
	
	
	//ActReportsVisits
	@RequestMapping(value = {"/report/ActReportsVisits"}, method = {RequestMethod.POST})
	@ResponseBody
	public String ActReportsVisits(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("fechaIni") String fechaIni,
			@RequestParam("fechaFin") String fechaFin) {
		
		DataTableObject dataTableObject = new DataTableObject();
		ArrayList<VisitScheduleBean> listVisitSchedule = this.serviceVisit.selectVisitSchedule();
		
		dataTableObject.setAaData(listVisitSchedule);
		dataTableObject.setiTotalDisplayRecords(listVisitSchedule.size());
		dataTableObject.setiTotalRecords(listVisitSchedule.size());
		//Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create(); 
		//Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
		String json = gson.toJson(dataTableObject);
		
		System.out.println(json);
		return json;
	}
	
}
