package org.silix.the9ull.microbit.control;

import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
@Named
public class RegisterBean implements RegisterBeanRemote {

	@Override
	public boolean register(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
