package com.bargadss.Propozal.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
public SessionFactory createSessionFactory() {
		
		// configuring hibernate
		Configuration configuration = new Configuration().configure();
		System.out.println("\nPropozal Configuration done\n");

		// create sessionfactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		System.out.println("\nPropozal SessionFactory created\n");
		
		return sessionFactory;
	}

}
