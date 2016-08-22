package com.bargadss.Propozal.Admin.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
public SessionFactory createSessionFactory() {
		
		// configuring hibernate
		Configuration configuration = new Configuration().configure();
		System.out.println("\nPropozal Admin Configuration done\n");

		// create sessionfactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		System.out.println("\nPropozal Admin SessionFactory created\n");
		
		return sessionFactory;
	}

}
