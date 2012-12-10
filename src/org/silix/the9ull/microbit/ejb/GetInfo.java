package org.silix.the9ull.microbit.ejb;

import javax.ejb.EJBObject;

public interface GetInfo extends EJBObject {

	public java.lang.Integer getNumUsers()
		    throws java.rmi.RemoteException;
	
	public java.lang.Double getValueBtcEur()
		    throws java.rmi.RemoteException;
	public java.lang.Double getValueBtcUsd()
		    throws java.rmi.RemoteException;
	

}
