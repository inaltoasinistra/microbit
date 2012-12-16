package org.silix.the9ull.microbit.control;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class GetInfo implements GetInfoRemote {

	@Override
	public long numberOfUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double valueBtcUsd() {
		// TODO Auto-generated method stub
		return 12.84;
	}

	@Override
	public double valueBtcEur() {
		// TODO Auto-generated method stub
		return 10.10;
	}
	
}
