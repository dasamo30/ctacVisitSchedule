package com.ctac.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ctac.bean.VisitScheduleBean;
import com.ctac.bean.VisitorBean;
import com.ctac.service.ServiceVisit;

@Controller
@RequestMapping({"/visit"})
public class ControllerConsult {
	
	private ServiceVisit serviceVisit= new ServiceVisit();
	
	@RequestMapping({"/consult"})
	public ModelAndView WiewPanelVisitor(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String namemenu = "Consult";
		String titlemenu = "init";
		HashMap route = new HashMap();
		route.put(Integer.valueOf(1), "Visit");
		route.put(Integer.valueOf(2), "Consult");
		String opmnu = "#lim_4:#lim_15";
		ModelAndView mv = new ModelAndView();
		mv.addObject("menus", sesion.getAttribute("lmemus"));
		//mv.addObject("foto", sesion.getAttribute("foto"));
		mv.addObject("namemenu", namemenu);
		mv.addObject("titlemenu", titlemenu);
		mv.addObject("funtion",
				"<script src=\"" + request.getContextPath() + "/js/visits.js\" type=\"text/javascript\" ></script>");
		mv.addObject("route", route);
		mv.addObject("opmnu", opmnu);
		mv.setViewName("view_panel_consult");
		return mv;
	}
	
	@ModelAttribute("listVisitor")
	public ArrayList<VisitorBean> listVisitor() {
      ArrayList<VisitorBean> data=serviceVisit.selectVisitorBean();		
      return data;
    }
	

	@RequestMapping(value = {"/consult/ActConsultVisitor"}, method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView ActViewModifVisitor(
			@RequestParam("call_cod") String call_cod,
			HttpServletRequest request) {
		
		ArrayList<VisitScheduleBean> lisVisitSchedule = new ArrayList<>();
		
		//VisitorBean v = serviceVisit.selectVisitorById(id_visitor);
				//new EmployeeBean();
		//e.setId_employee(id_employee);
		
		VisitScheduleBean visitSchedule=serviceVisit.selectVisitScheduleByCallcod(call_cod);
		if(visitSchedule!=null) {
		visitSchedule.setListVisitorLog(serviceVisit.selectVisitorLog(visitSchedule.getId_visit_schedule()));
		lisVisitSchedule.add(visitSchedule);
		}
		
		System.out.println(lisVisitSchedule.toString());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("view_consult_visitor");
		mav.addObject("lisVisitSchedule", lisVisitSchedule);
		//mav.addObject("formVisitor", "formModifVisitor");
		//mav.addObject("VisitorBean", v);
		return mav;
	}

}
