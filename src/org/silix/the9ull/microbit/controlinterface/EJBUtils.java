package org.silix.the9ull.microbit.controlinterface;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.silix.the9ull.microbit.control.GetInfo;

public class EJBUtils {
	static Context context = EJBContainer.createEJBContainer().getContext();
	
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
	
	static public GetInfo getGetInfo() {
		GetInfo gi = (GetInfo) lookup("java:global/Microbi/GetInfo");
		if(gi==null)
			assert(false); //bug
		return gi;
	}

}
