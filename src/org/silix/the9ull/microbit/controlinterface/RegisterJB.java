package org.silix.the9ull.microbit.controlinterface;

import java.rmi.RemoteException;

import org.silix.the9ull.microbit.control.RegisterBeanRemote;
import org.silix.the9ull.microbit.model.UserP;

public class RegisterJB {

	private String email;
	private String password;
	private String confirm;
	private int error;
	/* 1 → Password not validated
	 * 2 → RemoteException
	 */
	
	private boolean registered = false;
	
	private String new_deposit_address;
	private int new_id;
	
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
		System.out.println("isRegistered()");
		// EJB Client!
		if(!registered){
			
			if(getPassword().equals(getConfirm())){

				RegisterBeanRemote rb = EJBUtils.getRegister();
				
				UserP user = null;
				try {
					user = rb.register(getEmail(), getPassword());
				} catch (RemoteException e) {
					setError(2); // RemoteException
					return false;
				}
				
				// Take registration data
				setNew_deposit_address(user.getDeposit_address());
				setNew_id(user.getId());
				registered = true;
			} else {
				setError(1); // Password not validated
			}
		}
		return registered;
	}
	
	public void setRegistered(boolean registered) {
		//nothing to do here?
		this.registered = registered;  
	}
	
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}


	public String getNew_deposit_address() {
		System.out.println("DEBUG get: "+this.new_deposit_address);
		return new_deposit_address;
	}


	public void setNew_deposit_address(String new_deposit_address) {
		this.new_deposit_address = new_deposit_address;
		System.out.println("DEBUG set: "+this.new_deposit_address);
	}


	public int getNew_id() {
		System.out.println("DEBUG get: "+this.new_id);
		return new_id;
	}


	public void setNew_id(int new_id) {
		this.new_id = new_id;
		System.out.println("DEBUG set: "+this.new_id);
	}
}
