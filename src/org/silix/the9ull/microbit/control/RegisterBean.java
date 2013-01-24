package org.silix.the9ull.microbit.control;

import java.math.BigDecimal;
import java.rmi.RemoteException;

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
	public UserP register(String email, String password) throws RemoteException {
		
		System.out.println("Registering a new user.");
		
		UserP newUser;
		
		Session session = SingletonSessionFactory.getSession();
		Transaction tx = session.beginTransaction();//needed by newUser()
		
	
		try {
			newUser = newUser(email, password, session);
		} catch (BitcoinConnectionException e) {
			tx.rollback();
			SingletonSessionFactory.closeSession(session);
			throw new RemoteException();
		}
		if(newUser==null){
			tx.commit();
			SingletonSessionFactory.closeSession(session);
			return null;
		}
		tx.commit();
		SingletonSessionFactory.closeSession(session);
		
		return newUser;
	}
	
	public static UserP newUser(String email, String password, Session session) throws BitcoinConnectionException {
		// TODO: move this code out of here
		int id_user;
		Bitcoin bc = new Bitcoin(true);
		UserP user = new UserP();
		
		user.setEmail(email);
		user.setFund(new BigDecimal(0.0));
		user.setPassword("Just a second");
		id_user = (Integer) session.save(user);
		System.out.println("User_id: "+id_user);
		user.setPassword(SHA1.HMAC_digest(""+id_user,password));
		System.out.println("Password: "+user.getPassword());
		
		//deposit address
		String daddress = bc.getnewaddress("user"+id_user);
		if(daddress==null){
			System.out.println("Error: Is Bitcoin started?");
			return null;
		}
		System.out.println("New address: "+daddress);
		
		user.setDeposit_address(daddress);
		session.update(user);
		return user;
	}

	public void ejbRemove() {
		System.out.println("RegisterBean: ejbRemove");
	}
}
