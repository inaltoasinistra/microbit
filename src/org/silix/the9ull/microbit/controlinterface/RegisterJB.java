package org.silix.the9ull.microbit.controlinterface;

public class RegisterJB {

	private String email;
	private String password;
	private String confirm;
	
	private boolean registered = false;
	
	public RegisterJB() {
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
		// EJB Client!
		if(!registered){
			
			
			registered = true;
		}
		return registered;
	}
	
	public void setRegistered(boolean registered) {
		//nothing to do here
	}
}
