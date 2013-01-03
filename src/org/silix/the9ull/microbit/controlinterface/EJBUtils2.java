package org.silix.the9ull.microbit.controlinterface;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;


import org.silix.the9ull.microbit.control.GetInfo;

public class EJBUtils2 {
	EJBContainer container;
	Properties properties;
	
	public EJBUtils2() {
		properties = new Properties();
		properties.setProperty(EJBContainer.PROVIDER, "javax.ejb.spi.EJBContainerProvider");
		container = EJBContainer.createEJBContainer(properties);
		//container = EJBContainer.createEJBContainer();
	}
	
	private Object lookup(String path) {
		Object o = null;
		try {
			o = container.getContext().lookup(path);
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
		return o;		
	}
	
	public GetInfo getGetInfo() throws NamingException {
		GetInfo gi = (GetInfo) lookup("java:global/Microbi25/GetInfo");
		assert(gi instanceof GetInfo);
		if(gi==null)
			assert(false); //bug
		return gi;
	}

	void containerClose() {
		container.close();
	}
}
