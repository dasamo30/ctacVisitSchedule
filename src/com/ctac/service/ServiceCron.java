package com.ctac.service;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

import com.ctac.dao.DAOFactory;
import com.ctac.dao.IVisitDAO;

public class ServiceCron {
	
	
	//@Scheduled(cron="*/5 * * * * ?")
	@Scheduled(cron="0 5 0 * * *") 
    public void demoServiceMethod()
    {
        System.out.println("Inicio de limpieza  :: "+ new Date());
        DAOFactory fabrica = DAOFactory.getDAOFactory(3);
    	IVisitDAO visitDAO=fabrica.getVisitDAO();
    	System.out.println("Resultado de limpieza  :: "+visitDAO.updateStatusVisitScheduleCron()+" ::"+ new Date());
    	System.out.println("Fin de limpieza  :: "+ new Date());
    }
}
