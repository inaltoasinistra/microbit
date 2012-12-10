package org.silix.the9ull.microbit.control;

import java.util.regex.Pattern;

public class Login {

	private String idOrAddress;
	private int id;
	private String address;
	private String password;
	
	public Login() {
	}


	public void setIdOrAddress(String idOrAddress) {
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
		return false;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
