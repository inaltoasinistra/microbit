package org.silix.the9ull.microbit.control;

import java.net.ConnectException;

import javax.ejb.Stateless;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.silix.the9ull.microbit.model.PersistenceUtility;
import org.silix.the9ull.microbit.model.SingletonSessionFactory;
import org.silix.the9ull.microbit.model.UserP;

@Stateless
@Named
public class RegisterBean implements RegisterBeanRemote {

	@Override
	public UserP register(String email, String password) {
		
		System.out.println("Registering a new user.");
		
		UserP newUser;
		
		Session session = SingletonSessionFactory.getSession();
		Transaction tx = session.beginTransaction();//needed by newUser()
		
		try {
			newUser = PersistenceUtility.newUser(email, password, session);
		} catch (ConnectException e) {
			SingletonSessionFactory.closeSession(session);
			return null;
		}
		
		tx.commit();
		SingletonSessionFactory.closeSession(session);
		
		return newUser;
	}

	public void ejbRemove() {
		System.out.println("RegisterBean: ejbRemove");
	}
}
