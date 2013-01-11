package org.silix.the9ull.microbit.control;

import java.math.BigDecimal;
import java.util.Set;

import org.silix.the9ull.microbit.model.ContactP;

public interface UserBeanRemote extends javax.ejb.EJBObject {
	public boolean login(int id, String password) throws java.rmi.RemoteException;
	public void logout() throws java.rmi.RemoteException;
	public BigDecimal getFund() throws java.rmi.RemoteException;
	public Set<ContactP> getContacts() throws java.rmi.RemoteException;
	public boolean newContact(String alias, String address) throws java.rmi.RemoteException;
}
