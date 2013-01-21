package org.silix.the9ull.microbit.control;

import javax.ejb.Remote;

import org.silix.the9ull.microbit.model.UserP;

@Remote
public interface RegisterBeanRemote {

	public UserP register(String email, String password) throws java.rmi.RemoteException; 
	
}
