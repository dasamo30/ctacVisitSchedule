package com.ctac.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ctac.bean.CompanyBean;
import com.ctac.bean.DepartmentBean;
import com.ctac.bean.EmployeeBean;
import com.ctac.bean.OccupationBean;
import com.ctac.bean.ReasonVisitBean;
import com.ctac.bean.VisitScheduleBean;
import com.ctac.bean.VisitorBean;
import com.google.gson.Gson;

public class VisitDAOImplements implements IVisitDAO {
	private Gson gson = new Gson();
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public int insertIntoCompany(CompanyBean companyBean) {
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			String sql = "INSERT INTO visits.company(company_name, status, registration_date) VALUES (:company_name , :status , :registration_date)";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("company_name", companyBean.getCompany_name());
			query.setParameter("status", companyBean.getStatus());
			query.setParameter("registration_date", companyBean.getRegistration_date());
			int result =query.executeUpdate();
			System.out.println("resultdet.executeUpdate:: "+result);
			 if (!tx.wasCommitted()){
	                tx.commit();
	                rpta=0;
	                System.out.println("Hibernate.wasCommitted:: "+tx.getLocalStatus());
	          }
	            
	        }catch (HibernateException e) {
	            if (tx!=null){
	            	System.out.println("HibernateException.rollback:: "+e.getMessage());
	                tx.rollback();
	            }
	            System.out.println("HibernateException:: "+e.getMessage());
	            e.printStackTrace(); 
	        }finally {
	         System.out.println("Hibernate.session.close::");	
	          session.close();
	        }	

		return rpta;
	}
	

	@Override
	public int updateCompany(CompanyBean companyBean) {
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			String sql = "UPDATE visits.company\n" + 
					"SET company_name= :company_name \n" + 
					"WHERE id_company= :id_company";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("company_name", companyBean.getCompany_name());
			query.setParameter("id_company", companyBean.getId_company());
			int result =query.executeUpdate();
			System.out.println("resultdet.executeUpdate:: "+result);
			 if (!tx.wasCommitted()){
	                tx.commit();
	                rpta=0;
	                System.out.println("Hibernate.wasCommitted:: "+tx.getLocalStatus());
	          }
	            
	        }catch (HibernateException e) {
	            if (tx!=null){
	            	System.out.println("HibernateException.rollback:: "+e.getMessage());
	                tx.rollback();
	            }
	            System.out.println("HibernateException:: "+e.getMessage());
	            e.printStackTrace(); 
	        }finally {
	         System.out.println("Hibernate.session.close::");	
	          session.close();
	        }	

		return rpta;
	}

	@Override
	public int deleteCompany(CompanyBean companyBean) {
		int rpta = -1;
		Transaction tx = null;
		System.out.println(companyBean.toString());
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			String sql = "UPDATE visits.company \n" + 
					"SET status= :status \n" + 
					"WHERE id_company= :id_company";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("status", companyBean.getStatus());
			query.setParameter("id_company", companyBean.getId_company());
			int result =query.executeUpdate();
			System.out.println("resultdet.executeUpdate:: "+result);
			 if (!tx.wasCommitted()){
	                tx.commit();
	                rpta=0;
	                System.out.println("Hibernate.wasCommitted:: "+tx.getLocalStatus());
	          }
	            
	        }catch (HibernateException e) {
	            if (tx!=null){
	            	System.out.println("HibernateException.rollback:: "+e.getMessage());
	                tx.rollback();
	            }
	            System.out.println("HibernateException:: "+e.getMessage());
	            e.printStackTrace(); 
	        }finally {
	         System.out.println("Hibernate.session.close::");	
	          session.close();
	        }	

		return rpta;
	}

	@Override
	public ArrayList<CompanyBean> selectCompanyBean() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		//ArrayList litsPerfilBean = new ArrayList();
		ArrayList<CompanyBean> listCompanyBean=new ArrayList<>();
		try {
			//tx = session.beginTransaction();
			String e = "SELECT id_company, company_name, status, registration_date FROM visits.company where status <> 0";
			SQLQuery query = session.createSQLQuery(e);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			
			for(Object object : data)
	         {
	            Map row = (Map)object;
	            CompanyBean company=new CompanyBean();
	            company.setId_company((int) row.get("id_company"));
	            company.setCompany_name((String) row.get("company_name"));
	            company.setStatus((short) row.get("status"));
	            company.setRegistration_date((Date) row.get("registration_date"));
	            listCompanyBean.add(company);
	         }
				

			/*
			if (!tx.wasCommitted()) {
				tx.commit();
			}*/
		} catch (HibernateException e) {
			/*if (tx != null) {
				tx.rollback();
			}*/

			listCompanyBean = null;
			e.printStackTrace();
		} finally {
			session.close();
		}

		return listCompanyBean;
	}


	@Override
	public int insertIntoEmployee(EmployeeBean employeeBean) {
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			String sql = "INSERT INTO visits.employee( full_name, idcard, id_occupation, registration_date, status) "
					+ "VALUES ( :full_name , :idcard , :id_occupation , :registration_date , :status)";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("full_name", employeeBean.getFull_name());
			query.setParameter("idcard", employeeBean.getIdcard());
			query.setParameter("id_occupation", employeeBean.getId_occupation());
			query.setParameter("status", employeeBean.getStatus());
			query.setParameter("registration_date", employeeBean.getRegistration_date());
			int result =query.executeUpdate();
			System.out.println("resultdet.executeUpdate:: "+result);
			 if (!tx.wasCommitted()){
	                tx.commit();
	                rpta=0;
	                System.out.println("Hibernate.wasCommitted:: "+tx.getLocalStatus());
	          }
	            
	        }catch (HibernateException e) {
	            if (tx!=null){
	            	System.out.println("HibernateException.rollback:: "+e.getMessage());
	                tx.rollback();
	            }
	            System.out.println("HibernateException:: "+e.getMessage());
	            e.printStackTrace(); 
	        }finally {
	         System.out.println("Hibernate.session.close::");	
	          session.close();
	        }	

		return rpta;
	}


	@Override
	public int updateEmployee(EmployeeBean employeeBean) {
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			String sql = "UPDATE visits.employee\n" + 
					"SET full_name= :full_name, idcard= :idcard, id_occupation=:id_occupation \n" + 
					"WHERE id_employee= :id_employee";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("full_name", employeeBean.getFull_name());
			query.setParameter("idcard", employeeBean.getIdcard());
			query.setParameter("id_occupation", employeeBean.getId_occupation());
			query.setParameter("id_employee", employeeBean.getId_employee());
			int result =query.executeUpdate();
			System.out.println("resultdet.executeUpdate:: "+result);
			 if (!tx.wasCommitted()){
	                tx.commit();
	                rpta=0;
	                System.out.println("Hibernate.wasCommitted:: "+tx.getLocalStatus());
	          }
	            
	        }catch (HibernateException e) {
	            if (tx!=null){
	            	System.out.println("HibernateException.rollback:: "+e.getMessage());
	                tx.rollback();
	            }
	            System.out.println("HibernateException:: "+e.getMessage());
	            e.printStackTrace(); 
	        }finally {
	         System.out.println("Hibernate.session.close::");	
	          session.close();
	        }	

		return rpta;
	}


	@Override
	public int deleteEmployee(EmployeeBean employeeBean) {
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		System.out.println(employeeBean.toString());
		try {
			tx = session.beginTransaction();
			String sql = "UPDATE visits.employee \n" + 
					"SET status= :status \n" + 
					"WHERE id_employee= :id_employee";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("status", employeeBean.getStatus());
			query.setParameter("id_employee", employeeBean.getId_employee());
			int result =query.executeUpdate();
			System.out.println("resultdet.executeUpdate:: "+result);
			 if (!tx.wasCommitted()){
	                tx.commit();
	                rpta=0;
	                System.out.println("Hibernate.wasCommitted:: "+tx.getLocalStatus());
	          }
	            
	        }catch (HibernateException e) {
	            if (tx!=null){
	            	System.out.println("HibernateException.rollback:: "+e.getMessage());
	                tx.rollback();
	            }
	            System.out.println("HibernateException:: "+e.getMessage());
	            e.printStackTrace(); 
	        }finally {
	         System.out.println("Hibernate.session.close::");	
	          session.close();
	        }	

		return rpta;
	}


	@Override
	public ArrayList<EmployeeBean> selectEmployeeBean() {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		//ArrayList litsPerfilBean = new ArrayList();
		ArrayList<EmployeeBean> listEmployeeBean=new ArrayList<>();
		try {
			//tx = session.beginTransaction();
			String e = "SELECT a.id_employee, a.full_name, a.idcard, a.id_occupation, a.registration_date, a.status,\n" + 
					"       b.occupation\n" + 
					"FROM visits.employee a\n" + 
					"INNER JOIN visits.occupation b on b.id_occupation=a.id_occupation\n" + 
					"where a.status <> 0";
			SQLQuery query = session.createSQLQuery(e);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			
			for(Object object : data)
	         {
	            Map row = (Map)object;
	            EmployeeBean employee=new EmployeeBean();
	            employee.setId_employee((int) row.get("id_employee"));
	            employee.setFull_name((String) row.get("full_name"));
	            employee.setIdcard((String) row.get("idcard"));
	            employee.setId_occupation((int) row.get("id_occupation"));
	            employee.setStatus((short) row.get("status"));
	            employee.setRegistration_date((Date) row.get("registration_date"));
	            employee.setOccupation_name((String) row.get("occupation"));
	            listEmployeeBean.add(employee);
	         }
				

			/*
			if (!tx.wasCommitted()) {
				tx.commit();
			}*/
		} catch (HibernateException e) {
			/*if (tx != null) {
				tx.rollback();
			}*/

			listEmployeeBean = null;
			e.printStackTrace();
		} finally {
			session.close();
		}

		return listEmployeeBean;
	}


	@Override
	public EmployeeBean selectEmployeesById(int id_employee) {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		EmployeeBean employee=new EmployeeBean();
		
		try {
			//tx = session.beginTransaction();
			String e = "SELECT id_employee, full_name, idcard, id_occupation, registration_date, status FROM visits.employee where id_employee = :id_employee ";
			SQLQuery query = session.createSQLQuery(e);
			query.setParameter("id_employee", id_employee);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			HashMap map = (HashMap) data.get(0);
            employee.setId_employee((int) map.get("id_employee"));
            employee.setFull_name((String) map.get("full_name"));
            employee.setIdcard((String) map.get("idcard"));
            employee.setId_occupation((int) map.get("id_occupation"));
            employee.setStatus((short) map.get("status"));
            employee.setRegistration_date((Date) map.get("registration_date"));
	         
		} catch (HibernateException e) {
			/*if (tx != null) {
				tx.rollback();
			}*/

			employee = null;
			e.printStackTrace();
		} finally {
			session.close();
		}

		return employee;
	}


	@Override
	public int insertIntoVisitor(VisitorBean visitorBean) {
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			String sql = "INSERT INTO visits.visitors(full_name, number_license, citizen_ship, email, phone_number, status, registration_date)\n" + 
					"VALUES ( :full_name, :number_license, :citizen_ship, :email, :phone_number, :status, :registration_date)";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("full_name", visitorBean.getFull_name());
			query.setParameter("number_license", visitorBean.getNumber_license());
			query.setParameter("citizen_ship", visitorBean.getCitizen_ship());
			query.setParameter("email", visitorBean.getEmail());
			query.setParameter("phone_number", visitorBean.getPhone_number());
			query.setParameter("status", visitorBean.getStatus());
			query.setParameter("registration_date", visitorBean.getRegistration_date());
			int result =query.executeUpdate();
			System.out.println("resultdet.executeUpdate:: "+result);
			 if (!tx.wasCommitted()){
	                tx.commit();
	                rpta=0;
	                System.out.println("Hibernate.wasCommitted:: "+tx.getLocalStatus());
	          }
	            
	        }catch (HibernateException e) {
	            if (tx!=null){
	            	System.out.println("HibernateException.rollback:: "+e.getMessage());
	                tx.rollback();
	            }
	            System.out.println("HibernateException:: "+e.getMessage());
	            e.printStackTrace(); 
	        }finally {
	         System.out.println("Hibernate.session.close::");	
	          session.close();
	        }	

		return rpta;
	}


	@Override
	public int updateVisitor(VisitorBean visitorBean) {
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			String sql = "UPDATE visits.visitors \n" + 
					"SET full_name= :full_name, number_license= :number_license, citizen_ship= :citizen_ship, email= :email, phone_number= :phone_number \n" + 
					"WHERE id_visitor= :id_visitor";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("full_name", visitorBean.getFull_name());
			query.setParameter("number_license", visitorBean.getNumber_license());
			query.setParameter("citizen_ship", visitorBean.getCitizen_ship());
			query.setParameter("email", visitorBean.getEmail());
			query.setParameter("phone_number", visitorBean.getPhone_number());
			query.setParameter("id_visitor", visitorBean.getId_visitor());
			int result =query.executeUpdate();
			System.out.println("resultdet.executeUpdate:: "+result);
			 if (!tx.wasCommitted()){
	                tx.commit();
	                rpta=0;
	                System.out.println("Hibernate.wasCommitted:: "+tx.getLocalStatus());
	          }
	            
	        }catch (HibernateException e) {
	            if (tx!=null){
	            	System.out.println("HibernateException.rollback:: "+e.getMessage());
	                tx.rollback();
	            }
	            System.out.println("HibernateException:: "+e.getMessage());
	            e.printStackTrace(); 
	        }finally {
	         System.out.println("Hibernate.session.close::");	
	          session.close();
	        }	

		return rpta;
	}


	@Override
	public int deleteVisitor(VisitorBean visitorBean) {
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		System.out.println(visitorBean.toString());
		try {
			tx = session.beginTransaction();
			String sql = "UPDATE visits.visitors \n" + 
					"SET status= :status \n" + 
					"WHERE id_visitor= :id_visitor";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("status", visitorBean.getStatus());
			query.setParameter("id_visitor", visitorBean.getId_visitor());
			int result =query.executeUpdate();
			System.out.println("resultdet.executeUpdate:: "+result);
			 if (!tx.wasCommitted()){
	                tx.commit();
	                rpta=0;
	                System.out.println("Hibernate.wasCommitted:: "+tx.getLocalStatus());
	          }
	            
	        }catch (HibernateException e) {
	            if (tx!=null){
	            	System.out.println("HibernateException.rollback:: "+e.getMessage());
	                tx.rollback();
	            }
	            System.out.println("HibernateException:: "+e.getMessage());
	            e.printStackTrace(); 
	        }finally {
	         System.out.println("Hibernate.session.close::");	
	          session.close();
	        }	

		return rpta;
	}


	@Override
	public ArrayList<VisitorBean> selectVisitorBean() {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		//ArrayList litsPerfilBean = new ArrayList();
		ArrayList<VisitorBean> listVisitorBean=new ArrayList<>();
		try {
			//tx = session.beginTransaction();
			String e = "SELECT id_visitor, full_name, number_license, citizen_ship, email, phone_number, status, registration_date FROM visits.visitors where status <> 0";
			SQLQuery query = session.createSQLQuery(e);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			
			for(Object object : data)
	         {
	            Map row = (Map)object;
	            VisitorBean visitor=new VisitorBean();
	            visitor.setId_visitor((int) row.get("id_visitor"));
	            visitor.setFull_name((String) row.get("full_name"));
	            visitor.setNumber_license((String) row.get("number_license"));
	            visitor.setCitizen_ship(String.valueOf(row.get("citizen_ship")));
	            visitor.setEmail(String.valueOf(row.get("email")));
	            visitor.setPhone_number(String.valueOf(row.get("phone_number")));
	            visitor.setStatus((short) row.get("status"));
	            visitor.setRegistration_date((Date) row.get("registration_date"));
	            listVisitorBean.add(visitor);
	         }
				

			/*
			if (!tx.wasCommitted()) {
				tx.commit();
			}*/
		} catch (HibernateException e) {
			/*if (tx != null) {
				tx.rollback();
			}*/

			listVisitorBean = null;
			e.printStackTrace();
		} finally {
			session.close();
		}

		return listVisitorBean;
	}


	@Override
	public VisitorBean selectVisitorById(int id_visitor) {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		VisitorBean visitor=new VisitorBean();
		
		try {
			//tx = session.beginTransaction();
			String  sql= "SELECT id_visitor, full_name, number_license, citizen_ship, email, phone_number, \n" + 
					"       status, registration_date\n" + 
					"  FROM visits.visitors where id_visitor= :id_visitor";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("id_visitor", id_visitor);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			HashMap map = (HashMap) data.get(0);
			visitor.setId_visitor((int) map.get("id_visitor"));
			visitor.setFull_name((String) map.get("full_name"));
			visitor.setNumber_license((String) map.get("number_license"));
			visitor.setCitizen_ship(String.valueOf(map.get("citizen_ship")));
			visitor.setEmail(String.valueOf(map.get("email")));
			visitor.setPhone_number(String.valueOf(map.get("phone_number")));
			visitor.setStatus((short) map.get("status"));
			visitor.setRegistration_date((Date) map.get("registration_date"));
	         
		} catch (HibernateException e) {
			/*if (tx != null) {
				tx.rollback();
			}*/

			visitor = null;
			e.printStackTrace();
		} finally {
			session.close();
		}

		return visitor;
	}


	@Override
	public ArrayList<ReasonVisitBean> selectReasonVisitBean() {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		//ArrayList litsPerfilBean = new ArrayList();
		ArrayList<ReasonVisitBean> listeasonVisitBean=new ArrayList<>();
		try {
			//tx = session.beginTransaction();
			String e = "SELECT id_reason, reasons_name, status, registration_date FROM visits.reasons_visit where status=true";
			SQLQuery query = session.createSQLQuery(e);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			
			for(Object object : data)
	         {
	            Map row = (Map)object;
	            ReasonVisitBean reasonVisit=new ReasonVisitBean();
	            reasonVisit.setId_reason((int) row.get("id_reason"));
	            reasonVisit.setReasons_name((String) row.get("reasons_name"));
	            reasonVisit.setStatus((Boolean) row.get("status"));
	            reasonVisit.setRegistration_date((Date) row.get("registration_date"));
	            listeasonVisitBean.add(reasonVisit);
	         }
				

			/*
			if (!tx.wasCommitted()) {
				tx.commit();
			}*/
		} catch (HibernateException e) {
			/*if (tx != null) {
				tx.rollback();
			}*/

			listeasonVisitBean = null;
			e.printStackTrace();
		} finally {
			session.close();
		}

		return listeasonVisitBean;
	}


	@Override
	public String insertIntoVisitSchedule(VisitScheduleBean visitSchedule) {
		String rpta = "-1";
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			/*
			 INSERT INTO visits.visit_schedule(date_hour, id_company, id_employee, 
       id_visitor, registration_date, id_usuario, id_reason, status, id_department) 
VALUES ('2018-03-14 08:00:00',3,1,1,now(),1,3,1,1) RETURNING call_cod;
			 */
			
			String sql = "INSERT INTO visits.visit_schedule(\n" + 
					"date_hour, id_company, id_employee, id_visitor, registration_date, id_usuario, id_reason, status,id_department)\n" + 
					"VALUES (:date_hour, :id_company, :id_employee, :id_visitor, :registration_date, :id_usuario, :id_reason, :status, :id_department) RETURNING call_cod; ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("date_hour", visitSchedule.getDate_hour());
			query.setParameter("id_company", visitSchedule.getId_company());
			query.setParameter("id_employee", visitSchedule.getId_employee());
			query.setParameter("id_visitor", visitSchedule.getId_visitor());
			query.setParameter("id_usuario", visitSchedule.getId_usuario());
			query.setParameter("id_reason", visitSchedule.getId_reason());
			query.setParameter("status", visitSchedule.getStatus());
			query.setParameter("registration_date", visitSchedule.getRegistration_date());
			query.setParameter("id_department", visitSchedule.getId_department());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			String call_cod = (String) ((HashMap) data.get(0)).get("call_cod");
			System.out.println("id_visit_schedule::" + call_cod);
			
			//System.out.println("resultdet.executeUpdate:: "+result);
			 if (!tx.wasCommitted()){
	                tx.commit();
	                rpta=call_cod;
	                System.out.println("Hibernate.wasCommitted:: "+tx.getLocalStatus());
	          }
	            
	        }catch (HibernateException e) {
	            if (tx!=null){
	            	System.out.println("HibernateException.rollback:: "+e.getMessage());
	                tx.rollback();
	            }
	            System.out.println("HibernateException:: "+e.getMessage());
	            e.printStackTrace(); 
	        }finally {
	         System.out.println("Hibernate.session.close::");	
	          session.close();
	        }	

		return rpta;
	}


	@Override
	public int updateVisitSchedule(VisitScheduleBean visitSchedule) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int deleteVisitSchedule(VisitScheduleBean visitSchedule) {
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		System.out.println(visitSchedule.toString());
		try {
			tx = session.beginTransaction();
			String sql = "DELETE FROM visits.visit_schedule\n" + 
					" WHERE id_visit_schedule= :id_visit_schedule ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("id_visit_schedule", visitSchedule.getId_visit_schedule());
			int result =query.executeUpdate();
			System.out.println("resultdet.executeUpdate:: "+result);
			 if (!tx.wasCommitted()){
	                tx.commit();
	                rpta=0;
	                System.out.println("Hibernate.wasCommitted:: "+tx.getLocalStatus());
	          }
	            
	        }catch (HibernateException e) {
	            if (tx!=null){
	            	System.out.println("HibernateException.rollback:: "+e.getMessage());
	                tx.rollback();
	            }
	            System.out.println("HibernateException:: "+e.getMessage());
	            e.printStackTrace(); 
	        }finally {
	         System.out.println("Hibernate.session.close::");	
	          session.close();
	        }	

		return rpta;
	}


	@Override
	public ArrayList<VisitScheduleBean> selectVisitSchedule() {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		//ArrayList litsPerfilBean = new ArrayList();
		ArrayList<VisitScheduleBean> listVisitSchedule=new ArrayList<>();
		try {
			//tx = session.beginTransaction();
			String e = "SELECT a.id_visit_schedule, a.date_hour, a.badge_number, a.id_company, a.id_employee,a.id_department, \n" + 
					"       a.id_visitor, a.registration_date, a.id_usuario, a.id_reason, a.status,a.call_cod,\n" + 
					"       b.full_name as full_name_visitor, b.number_license, b.citizen_ship, b.email, b.phone_number,\n" + 
					"       c.full_name as full_name_employee,\n" + 
					"       d.company_name,\n" + 
					"       e.reasons_name,\n" + 
					"       f.department\n" + 
					"  FROM visits.visit_schedule a\n" + 
					"  inner join visits.visitors b on b.id_visitor=a.id_visitor\n" + 
					"  inner join visits.employee c on c.id_employee=a.id_employee\n" + 
					"  inner join visits.company d on d.id_company=a.id_company\n" + 
					"  inner join visits.reasons_visit e on e.id_reason=a.id_reason\n" + 
					"  inner join visits.department f on f.id_department=a.id_department\n" + 
					"  where\n" + 
					"  a.status=1 \n" + 
					"  order by a.id_visit_schedule desc";
			SQLQuery query = session.createSQLQuery(e);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			
			for(Object object : data)
	         {
	            Map row = (Map)object;
	            VisitScheduleBean visitSchedule=new VisitScheduleBean();
	            visitSchedule.setId_visit_schedule(((BigInteger) row.get("id_visit_schedule")).intValue());
	            visitSchedule.setDate_hour((Date) row.get("date_hour"));
	            visitSchedule.setId_company((int) row.get("id_company"));
	            visitSchedule.setId_employee((int) row.get("id_employee"));
	            visitSchedule.setId_visitor((int) row.get("id_visitor"));
	            visitSchedule.setId_department((int) row.get("id_department"));
	            visitSchedule.setId_usuario((int) row.get("id_usuario"));
	            visitSchedule.setId_reason((int) row.get("id_reason"));
	            visitSchedule.setStatus((short) row.get("status"));
	            visitSchedule.setRegistration_date((Date) row.get("registration_date"));
	            visitSchedule.setFull_name_visitor((String) row.get("full_name_visitor"));
	            visitSchedule.setNumber_license((String) row.get("number_license"));
	            visitSchedule.setCitizen_ship((String) row.get("citizen_ship"));
	            visitSchedule.setEmail((String) row.get("email"));
	            visitSchedule.setPhone_number((String) row.get("phone_number"));
	            visitSchedule.setFull_name_employee((String) row.get("full_name_employee"));
	            visitSchedule.setCompany_name((String) row.get("company_name"));
	            visitSchedule.setReasons_name((String) row.get("reasons_name"));
	            visitSchedule.setDepartment_name((String) row.get("department"));
	            visitSchedule.setCall_cod((String) row.get("call_cod"));

	            listVisitSchedule.add(visitSchedule);
	         }
				

			/*
			if (!tx.wasCommitted()) {
				tx.commit();
			}*/
		} catch (HibernateException e) {
			/*if (tx != null) {
				tx.rollback();
			}*/

			listVisitSchedule = null;
			e.printStackTrace();
		} finally {
			session.close();
		}

		return listVisitSchedule;
	}


	@Override
	public VisitScheduleBean selectVisitScheduleById(int id_visit_schedule) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public VisitScheduleBean selectVisitScheduleByIdByVisitor(String codeorname) {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		VisitScheduleBean visitSchedule=null;
		String date=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		try {
			//tx = session.beginTransaction();
			String  sql= "SELECT a.id_visit_schedule, a.date_hour, a.badge_number, a.id_company, a.id_employee,a.id_department, \n" + 
					"       a.id_visitor, a.registration_date, a.id_usuario, a.id_reason, a.status,a.call_cod,\n" + 
					"       b.full_name as full_name_visitor, b.number_license, b.citizen_ship, b.email, b.phone_number,\n" + 
					"       c.full_name as full_name_employee,g.occupation as occupation_employee,\n" + 
					"       d.company_name,\n" + 
					"       e.reasons_name,\n" + 
					"       f.department\n" + 
					"  FROM visits.visit_schedule a\n" + 
					"  inner join visits.visitors b on b.id_visitor=a.id_visitor\n" + 
					"  inner join visits.employee c on c.id_employee=a.id_employee\n" + 
					"  inner join visits.company d on d.id_company=a.id_company\n" + 
					"  inner join visits.reasons_visit e on e.id_reason=a.id_reason\n" + 
					"  inner join visits.department f on f.id_department=a.id_department\n" + 
					"  inner join visits.occupation g on g.id_occupation=c.id_occupation\n" +
					"  where\n" + 
					"  a.status=1\n" + 
					"  and \n" + 
					"  cast(a.date_hour as date)= cast( :datev as date) \n" + 
					"  and	\n" + 
					"  call_cod= :codeorname or b.full_name= :codeorname";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("codeorname", codeorname);
			query.setParameter("datev", date);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			System.out.println("size::"+data.size());
			if(data.size()>0) {
				visitSchedule=new VisitScheduleBean();
				HashMap map = (HashMap) data.get(0);
				visitSchedule.setId_visit_schedule(((BigInteger) map.get("id_visit_schedule")).intValue());
				visitSchedule.setDate_hour((Date) map.get("date_hour"));
				visitSchedule.setId_company((int) map.get("id_company"));
				visitSchedule.setId_employee((int) map.get("id_employee"));
				visitSchedule.setId_visitor((int) map.get("id_visitor"));
				visitSchedule.setId_usuario((int) map.get("id_usuario"));
				visitSchedule.setId_reason((int) map.get("id_reason"));
				visitSchedule.setId_department((int) map.get("id_department"));
	            visitSchedule.setStatus((short) map.get("status"));
	            visitSchedule.setRegistration_date((Date) map.get("registration_date"));
	            visitSchedule.setFull_name_visitor((String) map.get("full_name_visitor"));
	            visitSchedule.setNumber_license((String) map.get("number_license"));
	            visitSchedule.setCitizen_ship((String) map.get("citizen_ship"));
	            visitSchedule.setEmail((String) map.get("email"));
	            visitSchedule.setPhone_number((String) map.get("phone_number"));
	            visitSchedule.setFull_name_employee((String) map.get("full_name_employee"));
	            visitSchedule.setOccupation_employee((String) map.get("occupation_employee"));
	            visitSchedule.setCompany_name((String) map.get("company_name"));
	            visitSchedule.setReasons_name((String) map.get("reasons_name"));
	            visitSchedule.setDepartment_name((String) map.get("department"));
	            visitSchedule.setCall_cod((String) map.get("call_cod"));
			}
		} catch (HibernateException e) {
			visitSchedule = null;
			e.printStackTrace();
		} finally {
			session.close();
		}
		//System.out.println(visitSchedule.toString());
		return visitSchedule;
	}


	@Override
	public ArrayList<DepartmentBean> selectDepartmentBean() {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		//ArrayList litsPerfilBean = new ArrayList();
		ArrayList<DepartmentBean> listDepartment=new ArrayList<>();
		try {
			//tx = session.beginTransaction();
			String e = "SELECT id_department, department FROM visits.department where status=1";
			SQLQuery query = session.createSQLQuery(e);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			
			for(Object object : data)
	         {
	            Map row = (Map)object;
	            DepartmentBean department=new DepartmentBean();
	            department.setId_department((int) row.get("id_department"));
	            department.setDepartment((String) row.get("department"));
	            listDepartment.add(department);
	         }
				

			/*
			if (!tx.wasCommitted()) {
				tx.commit();
			}*/
		} catch (HibernateException e) {
			/*if (tx != null) {
				tx.rollback();
			}*/

			listDepartment = null;
			e.printStackTrace();
		} finally {
			session.close();
		}

		return listDepartment;
	}


	@Override
	public ArrayList<OccupationBean> selectOccupationBean() {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		//ArrayList litsPerfilBean = new ArrayList();
		ArrayList<OccupationBean> listOccupation=new ArrayList<>();
		try {
			//tx = session.beginTransaction();
			String e = "SELECT id_occupation, occupation FROM visits.occupation where status=1";
			SQLQuery query = session.createSQLQuery(e);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			
			for(Object object : data)
	         {
	            Map row = (Map)object;
	            OccupationBean occupation=new OccupationBean();
	            occupation.setId_occupation((int) row.get("id_occupation"));
	            occupation.setOccupation((String) row.get("occupation"));
	            listOccupation.add(occupation);
	         }
				

			/*
			if (!tx.wasCommitted()) {
				tx.commit();
			}*/
		} catch (HibernateException e) {
			/*if (tx != null) {
				tx.rollback();
			}*/

			listOccupation = null;
			e.printStackTrace();
		} finally {
			session.close();
		}

		return listOccupation;
	}


}
