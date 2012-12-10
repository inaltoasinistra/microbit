package org.silix.the9ull.microbit.ejb;

public interface GetInfoHome 
	extends javax.ejb.EJBHome{
		  public static final String COMP_NAME="java:comp/env/ejb/GetInfo";
		  public static final String JNDI_NAME="GetInfo";
		  
		  public org.silix.the9ull.microbit.ejb.GetInfo create()
		    throws javax.ejb.CreateException,java.rmi.RemoteException;
}
