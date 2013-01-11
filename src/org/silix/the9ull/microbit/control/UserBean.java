package org.silix.the9ull.microbit.control;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.Handle;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.RemoveException;
import javax.ejb.Stateful;
import javax.inject.Named;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.silix.the9ull.microbit.model.ContactP;
import org.silix.the9ull.microbit.model.SHA1;
import org.silix.the9ull.microbit.model.SingletonSessionFactory;
import org.silix.the9ull.microbit.model.Transactions;
import org.silix.the9ull.microbit.model.UserP;

@Stateful
@Named
public class UserBean implements UserBeanRemote {

	private Session session;
	private UserP user;
	private Transactions transactions;
	
	@Override
	public EJBHome getEJBHome() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Handle getHandle() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrimaryKey() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isIdentical(EJBObject arg0) throws RemoteException {
		UserBean u = (UserBean) arg0;
		return this.getUser().equals(u.getUser());
	}

	@Override
	public void remove() throws RemoteException, RemoveException {
		// TODO Auto-generated method stub		
	}

	@PostConstruct
	@PostActivate
	public void creation() {
		System.out.println("UserBean: creation && attivation");
		session = SingletonSessionFactory.getSession();
		try {
			transactions = Transactions.getInstance();
		} catch (IOException e) {
			System.out.println("UserBean: Transactions not loaded");
			e.printStackTrace();
		}
	}
	@PrePassivate
	@PreDestroy
	public void destruction() {
		System.out.println("UserBean: destruction && passivation");
		SingletonSessionFactory.closeSession(session);
		session = null;
	}

	// User API

	@Override
	public boolean login(int id, String password) throws RemoteException {
		user = (UserP) session.get(UserP.class,id);
		
		if(user==null)
			return false;
		return user.getPassword().equals(SHA1.HMAC_digest(""+id,password));
	}

	@Override
	@Remove
	public void logout() throws RemoteException {
		user = null;
	}

	@Override
	public BigDecimal getFund() throws RemoteException {
		// Login check
		if(user==null)
			return null;
		return user.getFund();
	}
	
	public void setFund(BigDecimal fund) throws RemoteException {
		
	}

	@Override
	public Set<ContactP> getContacts() throws RemoteException {
		if(user==null)
			return null;
		return user.getContacts();
	}

	public UserP getUser() {
		return user;
	}

	@Override
	public boolean newContact(String alias, String address)
			throws RemoteException {
		if(user==null)
			return false;
		Transaction tx = session.beginTransaction();
		ContactP c = new ContactP();
		c.setUser(getUser());
		c.setAlias(alias);
		c.setAddress(address);
		
		session.save(c); // And what's happen with existing contacts? 
		
		tx.commit();
		return false;
	}

	
}
