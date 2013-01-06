package org.silix.the9ull.microbit.controlinterface;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.silix.the9ull.microbit.control.GetInfoRemote;

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
	
	static public GetInfoRemote getGetInfo() throws NamingException {
		GetInfoRemote gi = (GetInfoRemote) lookup("java:global/Microbi25/GetInfo");
		assert(gi instanceof GetInfoRemote);
		assert(gi!=null);
		return gi;
	}

}
