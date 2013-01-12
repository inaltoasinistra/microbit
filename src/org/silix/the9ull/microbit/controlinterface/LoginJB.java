package org.silix.the9ull.microbit.controlinterface;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.silix.the9ull.microbit.control.GetInfoBeanRemote;
import org.silix.the9ull.microbit.control.UserBeanRemote;
import org.silix.the9ull.microbit.model.ContactP;

// Java Bean

public class LoginJB {

	private String idOrAddress;
	private int id;
	private String address;
	private String password;
	private boolean logged = false;
	
	//newContact
	private String newAlias;
	private String newAddress;
	private boolean addedContact;
	
	private Contacts contacts;
	
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
			if(this.getId() == -1){
				this.setId(gib.getIdFromAddress(this.getAddress()));
			} else {
				this.setAddress(gib.getAddressFromId(this.getId()));
			}
			try {
				this.logged = ub.login(this.getId(), this.getPassword());
				
				if(this.logged){
					contacts = new Contacts(this.ub);
				
				}
					
				System.out.println("id "+this.id);
				System.out.println("address "+this.address);
				
				System.out.println("Logged: "+this.logged);
				
			} catch (RemoteException e) {
				System.out.println("EJB server problem.");
				e.printStackTrace();
			}	
		}
		else {
			System.out.println("LoginJB: Logout");
			try {
				this.contacts.logout();
				this.contacts = null;
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

	public String getFund() {
		System.out.println("LoginJB: updateFund");
		try {
			return ub.getFund().toPlainString();
		} catch (RemoteException e) {
			System.out.println("EJB server problem.");
			e.printStackTrace();
		}
		return null;
	}

	public void setFund(String fund) {
		// read only value
	}

	public void updateFund() {
		
	}

	public String getContactsTable() {
		List<String> header = new LinkedList<String>();
		header.add("Alias");
		header.add("Address");
		return HTMLUtilities.printTable(contacts.get(), header);
	}


	public String getNewAlias() {
		return newAlias;
	}


	public void setNewAlias(String newAlias) {
		this.newAlias = newAlias;
	}


	public String getNewAddress() {
		return newAddress;
	}


	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}
	
	public void addNewContact() {
		try {
			addedContact = ub.newContact(newAlias, newAddress);
		} catch (RemoteException e) {
			addedContact = false;
			System.out.println("EJB server problem.");
			e.printStackTrace();
		}
	}
	
	public void setAddedContact(boolean addedContact) {
		this.addedContact = addedContact;
	}


	public boolean isAddedContact() {
		return addedContact;
	}

}
