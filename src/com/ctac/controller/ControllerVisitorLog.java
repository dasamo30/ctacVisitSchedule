package com.ctac.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ctac.bean.VisitScheduleBean;
import com.ctac.bean.VisitorLogBean;
import com.ctac.service.ServiceVisit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class ControllerVisitorLog {
	private ServiceVisit serviceVisit= new ServiceVisit();
	
	@RequestMapping({"/visitorLog"})
	public ModelAndView WiewLogin(HttpServletRequest request, HttpServletResponse res) {
		//log.info("ModelAndView WiewLogin");
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy");
		String datefooter = ft.format(dNow);
		//HttpSession sesionOk = request.getSession();
		ModelAndView mv = null;
		//if (sesionOk.getAttribute("usuario") == null) {
			mv = new ModelAndView();
			mv.setViewName("view_visitor_log");
			mv.addObject("datefooter", datefooter);
		//} else {
			//mv = new ModelAndView("redirect:panel/home");
		//}

		return mv;
	}
	
	@RequestMapping(value = {"/visitorLog/ActSearchVisit"}, method = {RequestMethod.POST})
	@ResponseBody
	public String ActSearchVisit(@RequestParam("codeorname") String codeorname) {
		//CompanyBean companyBean=new CompanyBean();
		//companyBean.setId_company(codeorname);
		//companyBean.setStatus((short) 0);
		//int rpta = this.serviceVisit.deleteCompany(companyBean);
		HashMap<String,Object> resul = new HashMap<String,Object>();
		VisitScheduleBean visitSchedule=serviceVisit.selectVisitScheduleByIdByVisitor(codeorname);
		int op=1;
		if(visitSchedule!=null) {
		    String name1 = visitSchedule.getId_visit_schedule()+visitSchedule.getDate_hour().toString();
		    visitSchedule.setVisitorLog(serviceVisit.selectVisitorLog(visitSchedule.getId_visit_schedule()));
			op=0;
			System.out.println(visitSchedule.toString());
		}
		resul.put("op", op);
		resul.put("data", visitSchedule);
		
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create(); 
		String json = gson.toJson(resul);
		return json;
	}
	
	@RequestMapping(value = {"/visitorLog/ActRegisterVisit"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActRegisterVisit(@ModelAttribute VisitorLogBean visitorLog) {
		Date date =Calendar.getInstance().getTime();
		visitorLog.setRegistration_date(date);
		/*
		if(visitorLog.getType()==1) {
			visitorLog.setType((short) 2);
		}else if(visitorLog.getType()==2) {
			visitorLog.setType((short) 3);
			
		}*/
		int rpta=0;//serviceVisit.insertVisitorLog(visitorLog);
		
		
		System.out.println("ActRegisterVisit: "+visitorLog.toString());
		return rpta;
	}

}
