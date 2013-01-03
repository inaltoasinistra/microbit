package org.silix.the9ull.microbit.controlinterface;

import javax.naming.NamingException;

import org.silix.the9ull.microbit.control.GetInfo;

public class Test {

	public Test() {
		GetInfo gi = null;
		try {
			gi = EJBUtils.getGetInfo();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		System.out.println(gi.valueBtcEur());
		System.out.println(gi.valueBtcUsd());
		System.out.println(gi.numberOfUsers());
		
		EJBUtils.containerClose();
	}

	public static void main(String[] args) {
		
		new Test();
	}

}
