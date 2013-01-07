package org.silix.the9ull.microbit.controlinterface;

import java.util.regex.Pattern;

// Java Bean

public class LoginJB {

	private String idOrAddress;
	private int id;
	private String address;
	private String password;
	
	public LoginJB() {
	}


	public void setIdOrAddress(String idOrAddress) {
		id = -1;
		address = null;
		if(Pattern.matches("^\\d*$", idOrAddress)){
			id = Integer.parseInt(idOrAddress);
			
		}
		else {
			address = idOrAddress;
			
		}
		this.idOrAddress = idOrAddress;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getIdOrAddress() {
		return idOrAddress;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isLogged() {
		//So, I should call EJB and verify things...
		return false;
	}

}
