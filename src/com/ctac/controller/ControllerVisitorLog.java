package com.ctac.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
	public ModelAndView ActSearchVisit(@RequestParam("id_visitor") int id_visitor) {
		Date date =Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		ArrayList<VisitScheduleBean> listData=new ArrayList<>();
		
		ArrayList<VisitScheduleBean> lisVisitSchedule=serviceVisit.selectVisitScheduleByidVisitor(id_visitor);
		
		
		
		for (VisitScheduleBean vs : lisVisitSchedule) {

			ArrayList<VisitorLogBean> log=(ArrayList<VisitorLogBean>) serviceVisit.selectVisitorLog(vs.getId_visit_schedule()).stream()
					.filter(item -> item.getType()==2 && sdf.format(item.getRegistration_date()).equals(sdf.format(date))).collect(Collectors.toList());
			System.out.println("size arreglo*****************: "+log.size());
			if(log.size()!=1) {
				System.out.println(vs.toString());
				listData.add(vs);
			}
			
		}
		
		
		System.out.println("count"+listData.size());
		System.out.println(listData.toString());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("view_list_visits");
		mav.addObject("lisVisitSchedule", listData);
		return mav;
	}
	
	@RequestMapping(value = {"/visitorLog/ActSearchVisit222"}, method = {RequestMethod.POST})
	@ResponseBody
	public String ActSearchVisit222(@RequestParam("id_visitor") int id_visitor) {
		//CompanyBean companyBean=new CompanyBean();
		//companyBean.setId_company(codeorname);
		//companyBean.setStatus((short) 0);
		//int rpta = this.serviceVisit.deleteCompany(companyBean);
		
		HashMap<String,Object> resul = new HashMap<String,Object>();
		ArrayList<VisitScheduleBean> LisVisitSchedule=serviceVisit.selectVisitScheduleByidVisitor(id_visitor);
		int op=1;
		/*
		if(visitSchedule!=null) {
		    String name1 = visitSchedule.getId_visit_schedule()+visitSchedule.getDate_ini.toString();
		    visitSchedule.setVisitorLog(serviceVisit.selectVisitorLog(visitSchedule.getId_visit_schedule()));
			op=0;
			System.out.println(visitSchedule.toString());
		}*/
	
		resul.put("op", op);
		resul.put("data", LisVisitSchedule);
		
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create(); 
		String json = gson.toJson(resul);
		return json;
	}
	
	@RequestMapping(value = {"/visitorLog/ActRegisterVisit"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActRegisterVisit(@RequestBody List<VisitorLogBean> listVisit) {
		/*
		Date date =Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		for (VisitorLogBean v : listVisit) {
			System.out.println(sdf.format(v.getRegistration_date())+"=="+sdf.format(date));
			if(sdf.format(v.getRegistration_date()).equals(sdf.format(date))) {
				System.out.println("iguales");
			}else {
				System.out.println("diferente");
			}
		}
		*/
		
		
		int rpta=-1;
		System.out.println("count:"+listVisit.size());
		System.out.println(":::::::::::::::::::"+listVisit.toString());
		
		
		
		rpta=serviceVisit.insertVisitorLog(listVisit);
		return rpta;
	}
	@RequestMapping(value = {"/visitorLog/ActRegisterVisit222"}, method = {RequestMethod.POST})
	@ResponseBody
	public int ActRegisterVisit222(@ModelAttribute("listObject") String listObject) throws JsonParseException, JsonMappingException, IOException {//List<ListName> listObject
		Date date =Calendar.getInstance().getTime();
		
		System.out.println(listObject.toString());
		ObjectMapper a=new ObjectMapper();
		List<VisitorLogBean> myObjects = Arrays.asList(a.readValue(listObject, VisitorLogBean[].class));
		
		System.out.println("tamoño:"+myObjects.size());
		
		System.out.println("ActRegisterVisit: "+myObjects.toString());



		//visitorLog.setRegistration_date(date);
		int rpta=-1;
		/*
		if(visitorLog.getType()==1) {
			visitorLog.setType((short) 2);
		}else if(visitorLog.getType()==2) {
			visitorLog.setType((short) 3);
			
		}*/
		rpta=serviceVisit.insertVisitorLog(myObjects);
		
		
		return rpta;
	}
	
	@RequestMapping(value="/visitorLog/searchByName", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<VisitScheduleBean> searchByName(@ModelAttribute("term") @Validated String name, HttpServletRequest httpServletRequest)   {
        
        //System.out.println("name"+name);
       ArrayList<VisitScheduleBean> listVisitSchedule = this.serviceVisit.selectVisitScheduleByNameVisitor(name);
        		//serviceInventory.get_Product_Search(3, name);
    /*
        name
        java.util.Date fecha = new java.util.Date(); 
        location.setDate_creation(fecha);
        System.out.println("location:"+location.toString());
        return serviceInventory.registerLocation(location);*/
        
        return  listVisitSchedule;
        
    }

}
