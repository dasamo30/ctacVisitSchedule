package com.ctac.dao;

import java.util.ArrayList;
import java.util.List;

import com.ctac.bean.CompanyBean;
import com.ctac.bean.DepartmentBean;
import com.ctac.bean.EmployeeBean;
import com.ctac.bean.OccupationBean;
import com.ctac.bean.ReasonVisitBean;
import com.ctac.bean.VisitScheduleBean;
import com.ctac.bean.VisitorBean;
import com.ctac.bean.VisitorLogBean;

public interface IVisitDAO {
	
	public int insertIntoCompany(CompanyBean companyBean);
	public int updateCompany(CompanyBean companyBean);
	public int deleteCompany(CompanyBean companyBean);
	public ArrayList<CompanyBean> selectCompanyBean();
	
	public int insertIntoEmployee(EmployeeBean employeeBean);
	public int updateEmployee(EmployeeBean employeeBean);
	public int deleteEmployee(EmployeeBean employeeBean);
	public ArrayList<EmployeeBean> selectEmployeeBean();
	public EmployeeBean selectEmployeesById(int id_employee);
	
	public int insertIntoVisitor(VisitorBean visitorBean);
	public int updateVisitor(VisitorBean visitorBean);
	public int deleteVisitor(VisitorBean visitorBean);
	public ArrayList<VisitorBean> selectVisitorBean();
	public VisitorBean selectVisitorById(int id_visitor);
	
	public ArrayList<ReasonVisitBean> selectReasonVisitBean();
	
	public String insertIntoVisitSchedule(VisitScheduleBean visitSchedule);
	public int updateVisitSchedule(VisitScheduleBean visitSchedule);
	public int deleteVisitSchedule(VisitScheduleBean visitSchedule);
	public ArrayList<VisitScheduleBean> selectVisitSchedule();
	public VisitScheduleBean selectVisitScheduleByCallcod(String call_cod);
	
	public ArrayList<VisitScheduleBean> selectVisitScheduleByNameVisitor(String name);
	public ArrayList<VisitScheduleBean> selectVisitScheduleByidVisitor(int id_visitor);
	
	public ArrayList<DepartmentBean> selectDepartmentBean();
	public ArrayList<OccupationBean> selectOccupationBean();
	
	public int insertVisitorLog(List<VisitorLogBean> listVisitorLog);
	
	public ArrayList<VisitorLogBean> selectVisitorLog(int id_visit_schedule);
	
	public int updateStatusVisitSchedule(VisitScheduleBean visitSchedule);
}
