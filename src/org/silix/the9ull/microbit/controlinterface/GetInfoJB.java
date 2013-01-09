package org.silix.the9ull.microbit.controlinterface;

import java.math.BigDecimal;

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

	public BigDecimal getUsd() {
		if(gib==null){
			System.out.println("Error: gib not defined");
			return new BigDecimal(0);
		}
		return gib.valueBtcUsd();
	}

	public void setUsd(BigDecimal usd) {
	}

	public BigDecimal getEur() {
		if(gib==null){
			System.out.println("Error: gib not defined");
			return new BigDecimal(0);
		}
		return gib.valueBtcEur();
	}

	public void setEur(BigDecimal eur) {
	}

	public long getNusers() {
		return gib.numberOfUsers();
	}

	public void setNusers(long nusers) {
	}
	

}
