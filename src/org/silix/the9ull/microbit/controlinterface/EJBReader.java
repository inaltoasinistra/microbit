package org.silix.the9ull.microbit.controlinterface;

import javax.ejb.EJB;

import org.silix.the9ull.microbit.control.GetInfoRemote;

public class EJBReader {

	@EJB
	private GetInfoRemote gir;

	public GetInfoRemote getGir() {
		return gir;
	}	
}
