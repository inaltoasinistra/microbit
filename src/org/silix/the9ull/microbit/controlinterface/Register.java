package org.silix.the9ull.microbit.controlinterface;

public class Register {
	
	private String email;
	private String password;
	private String confirm;
	
	Register(){
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	public boolean isRegistered(){
		return false;
	}
	
	public void setRegistered() {
		//nothing to do here
	}
}
