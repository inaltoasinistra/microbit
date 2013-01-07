package org.silix.the9ull.microbit.control;

import javax.ejb.Remote;

@Remote
public interface RegisterBeanRemote {

	public boolean register(String email); 
	
}
