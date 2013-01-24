package org.silix.the9ull.microbit.model;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class SingletonSessionFactory {

	static private SessionFactory sessionFactory = null;
	
	static private Session session = null;
	/* A global session have to be used because an user behavior affect others
	 * (1 → 2 transaction affects user's 1 and user's 2 fund)
	 */
	
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
	
	public static Session getSession(){
		if(session==null) {
			session = getSessionFactory().openSession();
		}
		return session;
	}
	public static void closeSession(Session session){
		//session.close();
		// Close session? Nah. There's only one global session
	}
}