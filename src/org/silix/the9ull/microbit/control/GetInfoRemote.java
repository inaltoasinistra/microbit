package org.silix.the9ull.microbit.control;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote
public interface GetInfoRemote {
	
	public long numberOfUsers();
    public double valueBtcUsd();
    public double valueBtcEur();
}

