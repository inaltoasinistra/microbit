package org.silix.the9ull.microbit.controlinterface;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.silix.the9ull.microbit.control.GetInfo;

public class EJBUtils {
	static EJBContainer container;
	
	static {
		container = EJBContainer.createEJBContainer();
	}
	
	static private Object lookup(String path) {
		Object o = null;
		try {
			o = container.getContext().lookup(path);
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
		return o;		
	}
	
	static public GetInfo getGetInfo() throws NamingException {
		GetInfo gi = (GetInfo) lookup("java:global/Microbi/GetInfo");
		assert(gi instanceof GetInfo);
		if(gi==null)
			assert(false); //bug
		return gi;
	}

	static void containerClose() {
		container.close();
	}
}
