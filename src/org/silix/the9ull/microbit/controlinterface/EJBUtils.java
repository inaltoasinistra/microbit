package org.silix.the9ull.microbit.controlinterface;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.silix.the9ull.microbit.control.GetInfoBeanRemote;
import org.silix.the9ull.microbit.control.RegisterBeanRemote;
import org.silix.the9ull.microbit.control.UserBeanRemote;

public class EJBUtils {
	static Context context;
	
	static {
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
			assert(false);
		}
	}
	
	public EJBUtils() {
		
	}
	
	static private Object lookup(String path) {
		Object o = null;
		try {
			o = context.lookup(path);
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
		return o;		
	}
	
	static public GetInfoBeanRemote getGetInfo() {
		GetInfoBeanRemote o = (GetInfoBeanRemote) lookup("java:global/Microbi25/GetInfoBean");
		assert(o instanceof GetInfoBeanRemote);
		assert(o!=null);
		return o;
	}
	static public RegisterBeanRemote getRegister() {
		RegisterBeanRemote o = (RegisterBeanRemote) lookup("java:global/Microbi25/RegisterBean");
		assert(o instanceof RegisterBeanRemote);
		assert(o!=null);
		return o;
	}
	static public UserBeanRemote getUser() {
		UserBeanRemote o = (UserBeanRemote) lookup("java:global/Microbi25/UserBean");
		assert(o instanceof UserBeanRemote);
		assert(o!=null);
		return o;
	}
}
