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
import com.ctac.libraries.DataTableObject;
import com.ctac.service.ServiceVisit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping({"/visit"})
public class ControllerEmployee {
	
private ServiceVisit serviceVisit= new ServiceVisit();
	
	@RequestMapping({"/employee"})
	public ModelAndView WiewPanelEmployee(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String namemenu = "Employee";
		String titlemenu = "init";
		HashMap route = new HashMap();
		route.put(Integer.valueOf(1), "Visits");
		route.put(Integer.valueOf(2), "Employee");
		String opmnu = "#lim_4:#lim_8";
		ModelAndView mv = new ModelAndView();
		mv.addObject("menus", sesion.getAttribute("lmemus"));
		//mv.addObject("foto", sesion.getAttribute("foto"));
		mv.addObject("namemenu", namemenu);
		mv.addObject("titlemenu", titlemenu);
		mv.addObject("funtion",
				"<script src=\"" + request.getContextPath() + "/js/visits.js\" type=\"text/javascript\" ></script>");
		mv.addObject("route", route);
		mv.addObject("opmnu", opmnu);
		mv.setViewName("view_panel_employee");
		return mv;
	}
	
	
	@RequestMapping(value = {"/employee/ActViewEmployee"}, method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView ActViewUsuario() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("view_new_employee");
		mav.addObject("formEmployee", "formRegisterEmployee");
		return mav;
	}
	
	@RequestMapping(value = {"/employee/ActRegisterEmployee"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActRegisterEmployee(@RequestBody EmployeeBean employeeBean) {
		
		//SimpleDateFormat date=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date =Calendar.getInstance().getTime();
		
		employeeBean.setStatus((short) 1);
		employeeBean.setRegistration_date(date);
		System.out.println(employeeBean.toString());
		int rpta =this.serviceVisit.insertIntoEmployee(employeeBean);
		return rpta;
	}
	
	
	@RequestMapping(value = {"/employee/ActlistEmployee"}, method = {RequestMethod.POST})
	@ResponseBody
	public String ActlistEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		DataTableObject dataTableObject = new DataTableObject();
		ArrayList<EmployeeBean> listEmployee = this.serviceVisit.selectEmployeeBean();
		
		dataTableObject.setAaData(listEmployee);
		dataTableObject.setiTotalDisplayRecords(listEmployee.size());
		dataTableObject.setiTotalRecords(listEmployee.size());
		Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
		String json = gson.toJson(dataTableObject);
		
		System.out.println(json);
		return json;
	}
	
	
	@RequestMapping(value = {"/employee/ActViewModifEmployee"}, method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView ActViewModifEmployee(
			@RequestParam("id_employee") int id_employee,
			HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		EmployeeBean e = serviceVisit.selectEmployeesById(id_employee);
				//new EmployeeBean();
		//e.setId_employee(id_employee);
		
		mav.setViewName("view_new_employee");
		mav.addObject("formEmployee", "formModifEmployee");
		mav.addObject("EmployeeBean", e);
		return mav;
	}
	
	
	@RequestMapping(value = {"/employee/ActModifEmployee"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActModifEmployee(@RequestBody EmployeeBean employeeBean) {
		
		//SimpleDateFormat date=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		//Date date =Calendar.getInstance().getTime();
		
		//companyBean.setStatus((short) 1);
		//companyBean.setRegistration_date(date);
		System.out.println(employeeBean.toString());
		int rpta =this.serviceVisit.updateEmployee(employeeBean);
		return rpta;
	}
	
	@RequestMapping(value = {"/employee/ActDeleteEmployee"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActDeleteEmployee(@RequestParam("id_employee") int id_employee) {
		EmployeeBean employeeBean=new EmployeeBean();
		employeeBean.setId_employee(id_employee);
		int rpta = this.serviceVisit.deleteEmployee(employeeBean);
		return rpta;
	}
	 

}
