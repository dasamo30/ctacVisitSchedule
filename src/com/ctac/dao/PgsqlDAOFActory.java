package com.ctac.dao;

import com.ctac.dao.AcessoDAOImplements;
import com.ctac.dao.DAOFactory;
import com.ctac.dao.IAccesosDAO;
public class PgsqlDAOFActory extends DAOFactory {
	public IAccesosDAO getAccesosDAO() {
		return new AcessoDAOImplements();
	}

	@Override
	public IVisitDAO getVisitDAO() {
		// TODO Auto-generated method stub
		return new  VisitDAOImplements();
	}
}