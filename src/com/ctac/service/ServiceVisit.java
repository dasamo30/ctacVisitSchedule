package com.ctac.service;

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
	
	public String insertIntoVisitSchedule(VisitScheduleBean visitSchedule){
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
	public VisitScheduleBean selectVisitScheduleByCallcod(String call_cod){
		return visitDAO.selectVisitScheduleByCallcod(call_cod);
	}
	
	public ArrayList<VisitScheduleBean> selectVisitScheduleByNameVisitor(String name){
		return visitDAO.selectVisitScheduleByNameVisitor(name);
	}
	public ArrayList<VisitScheduleBean> selectVisitScheduleByidVisitor(int id_visitor){
		return visitDAO.selectVisitScheduleByidVisitor(id_visitor);
	}
	
	public ArrayList<DepartmentBean> selectDepartmentBean(){
		return visitDAO.selectDepartmentBean();
	}
	
	public ArrayList<OccupationBean> selectOccupationBean(){
		return visitDAO.selectOccupationBean();
	}
	
	public int insertVisitorLog(List<VisitorLogBean> listVisitorLog) {
		return visitDAO.insertVisitorLog(listVisitorLog);
	}
	public ArrayList<VisitorLogBean> selectVisitorLog(int id_visit_schedule){
		return visitDAO.selectVisitorLog(id_visit_schedule);
	}
	
	public int updateStatusVisitSchedule(VisitScheduleBean visitSchedule) {
		return visitDAO.updateStatusVisitSchedule(visitSchedule);
	}
}
