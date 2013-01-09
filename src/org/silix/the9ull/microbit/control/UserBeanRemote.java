package org.silix.the9ull.microbit.control;

public interface UserBeanRemote extends javax.ejb.EJBObject {
	public boolean login(int id, String password) throws java.rmi.RemoteException;;
	public boolean login(String address, String password) throws java.rmi.RemoteException;;
	public void logout() throws java.rmi.RemoteException;;
}
