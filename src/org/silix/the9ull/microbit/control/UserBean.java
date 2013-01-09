package org.silix.the9ull.microbit.control;

import java.rmi.RemoteException;

import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.Handle;
import javax.ejb.Remove;
import javax.ejb.RemoveException;
import javax.ejb.Stateful;
import javax.inject.Named;

@Stateful
@Named
public class UserBean implements UserBeanRemote {

	private int id;
	
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
		this.id = id;
		return true;
	}

	@Override
	public boolean login(String address, String password)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Remove
	public void logout() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	
}
