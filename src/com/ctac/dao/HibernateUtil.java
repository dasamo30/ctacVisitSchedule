package com.ctac.dao;


import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();
	private static final Logger log = Logger.getLogger(HibernateUtil.class);

	private static SessionFactory buildSessionFactory() {
		try {
			//return (new Configuration()).configure(new File("/opt/smsmasivo/etc/hibernate.cfg.xml"))
			//return (new Configuration()).configure("/resources/hibernate.cfg.xml").buildSessionFactory();
			return new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable arg0) {
			System.err.println("Initial SessionFactory creation failed." + arg0);
			throw new ExceptionInInitializerError(arg0);
		}
	}

	public static SessionFactory getSessionFactory() {
		log.info("hibernate::::::::::::::");
		return sessionFactory;
	}
}