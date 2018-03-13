package com.ctac.service;

import java.util.ArrayList;

import com.ctac.bean.CompanyBean;
import com.ctac.bean.EmployeeBean;
import com.ctac.bean.ReasonVisitBean;
import com.ctac.bean.VisitScheduleBean;
import com.ctac.bean.VisitorBean;
import com.ctac.dao.DAOFactory;
import com.ctac.dao.IVisitDAO;

public class ServiceVisit {
	private DAOFactory fabrica = DAOFactory.getDAOFactory(3);
	private IVisitDAO visitDAO=fabrica.getVisitDAO();
	
	public int insertIntoCompany(CompanyBean companyBean) {
		
		return visitDAO.insertIntoCompany(companyBean);
	}
	
	public ArrayList<CompanyBean> selectCompanyBean() {
		
		return visitDAO.selectCompanyBean();
	}
	
	public int updateCompany(CompanyBean companyBean) {
		return visitDAO.updateCompany(companyBean);
	}
	
	public int deleteCompany(CompanyBean companyBean) {
		return visitDAO.deleteCompany(companyBean);
	}
	
	public int insertIntoEmployee(EmployeeBean employeeBean){
		return visitDAO.insertIntoEmployee(employeeBean);
	}
	public int updateEmployee(EmployeeBean employeeBean){
		return visitDAO.updateEmployee(employeeBean);
	}
	
	public int deleteEmployee(EmployeeBean employeeBean){
		return visitDAO.deleteEmployee(employeeBean);
	}
	
	public ArrayList<EmployeeBean> selectEmployeeBean(){
		return visitDAO.selectEmployeeBean();
	}
	
	public EmployeeBean selectEmployeesById(int id_employee) {
		return visitDAO.selectEmployeesById(id_employee);
	}
	
	public int insertIntoVisitor(VisitorBean visitorBean) {
		return visitDAO.insertIntoVisitor(visitorBean);
	}
	
	public int updateVisitor(VisitorBean visitorBean){
		return visitDAO.updateVisitor(visitorBean);
	}
	
	public int deleteVisitor(VisitorBean visitorBean){
		return visitDAO.deleteVisitor(visitorBean);
	}
	
	public ArrayList<VisitorBean> selectVisitorBean(){
		return visitDAO.selectVisitorBean();
	}
	
	public VisitorBean selectVisitorById(int id_visitor){
		return visitDAO.selectVisitorById(id_visitor);
	}
	
	public ArrayList<ReasonVisitBean> selectReasonVisitBean(){
		return visitDAO.selectReasonVisitBean();
	}
	
	public int insertIntoVisitSchedule(VisitScheduleBean visitSchedule){
		return visitDAO.insertIntoVisitSchedule(visitSchedule);
	}
	public int updateVisitSchedule(VisitScheduleBean visitSchedule){
		return visitDAO.updateVisitSchedule(visitSchedule);
	}
	public int deleteVisitSchedule(VisitScheduleBean visitSchedule){
		return visitDAO.deleteVisitSchedule(visitSchedule);
	}
	public ArrayList<VisitScheduleBean> selectVisitSchedule(){
		return visitDAO.selectVisitSchedule();
	}
	public VisitScheduleBean selectVisitScheduleById(int id_visit_schedule){
		return visitDAO.selectVisitScheduleById(id_visit_schedule);
	}
	
	public VisitScheduleBean selectVisitScheduleByIdByVisitor(String codeorname) {
		return visitDAO.selectVisitScheduleByIdByVisitor(codeorname);
	}
}