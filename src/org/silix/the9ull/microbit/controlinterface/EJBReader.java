package org.silix.the9ull.microbit.controlinterface;

import javax.ejb.EJB;

import org.silix.the9ull.microbit.control.GetInfoRemote;

public class EJBReader {

	@EJB(mappedName="java:global/Microbi25/GetInfo")
	private GetInfoRemote gir;

	public GetInfoRemote getGir() {
		return gir;
	}	
}
