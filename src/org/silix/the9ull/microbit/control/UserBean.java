package org.silix.the9ull.microbit.control;

import java.rmi.RemoteException;

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
import org.silix.the9ull.microbit.model.SHA1;
import org.silix.the9ull.microbit.model.SingletonSessionFactory;
import org.silix.the9ull.microbit.model.UserP;

@Stateful
@Named
public class UserBean implements UserBeanRemote {

	private Session session;
	private UserP user;
	
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove() throws RemoteException, RemoveException {
		// TODO Auto-generated method stub
		
	}

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

	@PostConstruct
	@PostActivate
	public void creation() {
		System.out.println("UserBean: creation && attivation");
		session = SingletonSessionFactory.getSession();
	}
	@PrePassivate
	@PreDestroy
	public void destruction() {
		System.out.println("UserBean: destruction && passivation");
		SingletonSessionFactory.closeSession(session);
	}
	
}
