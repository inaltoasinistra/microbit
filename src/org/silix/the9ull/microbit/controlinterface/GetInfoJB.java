package org.silix.the9ull.microbit.controlinterface;

import org.silix.the9ull.microbit.control.GetInfoBeanRemote;

public class GetInfoJB {

	private GetInfoBeanRemote gib;
	
	public GetInfoJB() {
		 gib = EJBUtils.getGetInfo();
	}

	public GetInfoBeanRemote getGib() {
		return gib;
	}

	public void setGib(GetInfoBeanRemote gib) {
	}

	public double getUsd() {
		return gib.valueBtcUsd();
	}

	public void setUsd(double usd) {
	}

	public double getEur() {
		return gib.valueBtcEur();
	}

	public void setEur(double eur) {
	}

	public long getNusers() {
		return gib.numberOfUsers();
	}

	public void setNusers(long nusers) {
	}
	

}
