package org.silix.the9ull.microbit.control;

import java.net.ConnectException;

import org.hibernate.Session;
import org.silix.the9ull.microbit.model.PersistenceUtility;
import org.silix.the9ull.microbit.model.SingletonSessionFactory;

public class TestRegister {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String email = "stoc@qwe.asd";
		String password = "q";
		
		System.out.println("Registering a new user.");
		
		Session session = SingletonSessionFactory.getSession();
		
		try {
			PersistenceUtility.newUser(email, password, session);
		} catch (ConnectException e) {
			SingletonSessionFactory.closeSession(session);
			System.out.println("No");
		}
		
		SingletonSessionFactory.closeSession(session);
	}

}
