package com.ctac.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ctac.bean.CompanyBean;
import com.ctac.bean.DepartmentBean;
import com.ctac.bean.EmployeeBean;
import com.ctac.bean.ReasonVisitBean;
import com.ctac.bean.VisitScheduleBean;
import com.ctac.bean.VisitorBean;
import com.ctac.libraries.DataTableObject;
import com.ctac.service.ServiceVisit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping({"/visit"})
public class ControllerVisitSchedule {
	private ServiceVisit serviceVisit= new ServiceVisit();
	
	@RequestMapping({"/schedule"})
	public ModelAndView WiewPanelschedule(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String namemenu = "schedule";
		String titlemenu = "init";
		HashMap route = new HashMap();
		route.put(Integer.valueOf(1), "Visits");
		route.put(Integer.valueOf(2), "schedule");
		String opmnu = "#lim_4:#lim_5";
		ModelAndView mv = new ModelAndView();
		mv.addObject("menus", sesion.getAttribute("lmemus"));
		//mv.addObject("foto", sesion.getAttribute("foto"));
		mv.addObject("namemenu", namemenu);
		mv.addObject("titlemenu", titlemenu);
		mv.addObject("funtion",
				"<script src=\"" + request.getContextPath() + "/js/visits.js\" type=\"text/javascript\" ></script>");
		mv.addObject("route", route);
		mv.addObject("opmnu", opmnu);
		mv.setViewName("view_panel_schedule");
		return mv;
	}
	
	@ModelAttribute("listEmployee")
	public ArrayList<EmployeeBean> listEmployee() {
      ArrayList<EmployeeBean> data=serviceVisit.selectEmployeeBean();		
      return data;
    }
	
	@ModelAttribute("listCompany")
	public ArrayList<CompanyBean> listCompany() {
      ArrayList<CompanyBean> data=serviceVisit.selectCompanyBean();		
      return data;
    }
	
	@ModelAttribute("listVisitor")
	public ArrayList<VisitorBean> listVisitor() {
      ArrayList<VisitorBean> data=serviceVisit.selectVisitorBean();		
      return data;
    }
	
	@ModelAttribute("listReason")
	public ArrayList<ReasonVisitBean> listReason() {
      ArrayList<ReasonVisitBean> data=serviceVisit.selectReasonVisitBean();		
      return data;
    }
	
	@ModelAttribute("listDepartment")
	public ArrayList<DepartmentBean> listDepartment() {
      ArrayList<DepartmentBean> data=serviceVisit.selectDepartmentBean();		
      return data;
    }
	
	@RequestMapping(value = {"/schedule/ActViewSchedule"}, method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView ActViewSchedule() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("view_new_schedule");
		mav.addObject("formSchedule", "formRegisterSchedule");
		return mav;
	}
	
	
	@RequestMapping(value = {"/schedule/ActRegisterSchedule"}, method = {RequestMethod.POST})
	@ResponseBody
	public String ActRegisterSchedule(HttpServletRequest request,@RequestBody VisitScheduleBean visitScheduleBean) throws ParseException {
		HttpSession sesion = request.getSession();
		
		//SimpleDateFormat date=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date =Calendar.getInstance().getTime();
		
		//String date_hour=visitScheduleBean.getDate()+" "+visitScheduleBean.getHour();
      	//SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		
		visitScheduleBean.setStatus((short) 1);
		visitScheduleBean.setRegistration_date(date);
		visitScheduleBean.setId_usuario(Integer.parseInt(sesion.getAttribute("idusuario").toString()));
		//visitScheduleBean.setDate_hour(sf.parse(date_hour));
		
		System.out.println(visitScheduleBean.toString());
		
		String rpta =this.serviceVisit.insertIntoVisitSchedule(visitScheduleBean);
		System.out.println("respuesta::: "+rpta);
		return rpta;
	}
	
	
	@RequestMapping(value = {"/schedule/ActlistSchedule"}, method = {RequestMethod.POST})
	@ResponseBody
	public String ActlistSchedule(HttpServletRequest request, HttpServletResponse response) {
		
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
	
	/*
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
		
		System.out.println(visitorBean.toString());
		int rpta =this.serviceVisit.updateVisitor(visitorBean);
		return rpta;
	}
	*/
	@RequestMapping(value = {"/schedule/ActDeleteSchedule"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActDeleteSchedule(@RequestParam("id_visit_schedule") int id_visit_schedule) {
		VisitScheduleBean VisitSchedule=new VisitScheduleBean();
		VisitSchedule.setId_visit_schedule(id_visit_schedule);
		int rpta = this.serviceVisit.deleteVisitSchedule(VisitSchedule);
		return rpta;
	}

}
