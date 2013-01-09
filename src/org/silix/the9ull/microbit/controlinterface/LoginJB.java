package org.silix.the9ull.microbit.controlinterface;

import java.rmi.RemoteException;
import java.util.regex.Pattern;

import org.silix.the9ull.microbit.control.GetInfoBeanRemote;
import org.silix.the9ull.microbit.control.UserBeanRemote;

// Java Bean

public class LoginJB {

	private String idOrAddress;
	private int id;
	private String address;
	private String password;
	private boolean logged = false;
	
	private UserBeanRemote ub; //The Bean
	
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

	public void setLogged(boolean logged) {
		ub = EJBUtils.getUser();
		
		GetInfoBeanRemote gib = EJBUtils.getGetInfo();
		
		if(logged){
			try {
				if(this.getId() == -1){
					this.logged = ub.login(this.getAddress(), this.getPassword());
					this.setId(gib.getIdFromAddress(this.getAddress()));
				} else {
					this.logged = ub.login(this.getId(), this.getPassword());
					this.setAddress(gib.getAddressFromId(this.getId()));
				}
				
			} catch (RemoteException e) {
				System.out.println("EJB server problem.");
				e.printStackTrace();
			}	
		}
		else {
			System.out.println("LoginJB: Logout");
			try {
				ub.logout();
				this.logged = false;
			} catch (RemoteException e) {
				System.out.println("EJB server problem.");
				e.printStackTrace();	
			}
		}
	}
	
	public boolean isLogged() {
		return logged;
	}

}
