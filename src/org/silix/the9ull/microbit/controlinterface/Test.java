package org.silix.the9ull.microbit.controlinterface;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.silix.the9ull.microbit.control.GetInfo;
import org.silix.the9ull.microbit.control.GetInfoRemote;

public class Test {
	
	public Test() {
		final Context context = EJBContainer.createEJBContainer().getContext();
		GetInfo gi = null;

        try {
			gi = (GetInfo) context.lookup("java:global/Microbi25/GetInfo");
		} catch (NamingException e) {
			e.printStackTrace();
		}
       
        System.out.println(gi.valueBtcUsd());
	}

	public static void main(String[] args) {		
		new Test();
	}

}
