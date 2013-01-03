package org.silix.the9ull.microbit.controlinterface;

import javax.ejb.EJB;

import org.silix.the9ull.microbit.control.GetInfo;

public class TestTwo {

	@EJB
	private GetInfo gi;
	
	public TestTwo() {
		System.out.println(gi.numberOfUsers());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestTwo();
	}

}
