package org.silix.the9ull.microbit.control;

import javax.ejb.Remote;

@Remote
public interface GetInfoBeanRemote {
	
	public long numberOfUsers();
	
    public double valueBtcUsd();
    public double valueBtcEur();
    
    public int getIdFromAddress(String address);
    public String getAddressFromId(int id);
}

