package org.silix.the9ull.microbit;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class SingletonSessionFactory {

	static private SessionFactory sessionFactory = null;
	
	private SingletonSessionFactory() {
	}
	

	public static SessionFactory getSessionFactory() {
		Configuration configuration;
		ServiceRegistry serviceRegistry;
		
		if(sessionFactory==null){
			try {
				configuration = new Configuration();
				configuration.configure();
				serviceRegistry = new ServiceRegistryBuilder().applySettings(
						configuration.getProperties()).buildServiceRegistry();
						sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Throwable ex) {
				System.err.println("Failed to create sessionFactory object." + ex);
				throw new ExceptionInInitializerError(ex);
			}
			
		}
		return sessionFactory;
	}
}