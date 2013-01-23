package org.silix.the9ull.microbit.control;

import java.math.BigDecimal;

import javax.ejb.Remote;

@Remote
public interface GetInfoBeanRemote {
	
	public long numberOfUsers();
	
    public BigDecimal valueBtcUsd();
    public BigDecimal valueBtcEur();
    
    public Integer getIdFromAddress(String address);
    public String getAddressFromId(int id);
}

