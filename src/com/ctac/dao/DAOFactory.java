package com.ctac.dao;

import com.ctac.dao.IAccesosDAO;
import com.ctac.dao.PgsqlDAOFActory;

public abstract class DAOFactory {
	public static final int MYSQL = 1;
	public static final int ORACLE = 2;
	public static final int POSTGRES = 3;
	public static final int SQLSERVER = 4;

	public abstract IAccesosDAO getAccesosDAO();

	public abstract IVisitDAO getVisitDAO();

	public static DAOFactory getDAOFactory(int db) {
		switch (db) {
			case 3 :
				return new PgsqlDAOFActory();
			default :
				return null;
		}
	}
}