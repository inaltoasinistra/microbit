package org.silix.the9ull.microbit.controlinterface;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.silix.the9ull.microbit.control.GetInfoBeanRemote;
import org.silix.the9ull.microbit.control.UserBeanRemote;
import org.silix.the9ull.microbit.model.Tx;

// Java Bean

public class LoginJB {

	private String idOrAddress;
	private int id;
	private String address;
	private String password;
	private boolean logged = false;
	
	//newContact
	private String alias;
	private String contactAddress;
	private boolean addedContact;
	private boolean removedContact;
	private String sendTo;
	private String howMuch;
	private boolean sentTo;
	private BigDecimal fee;
	

	private Contacts contacts;
	
	private UserBeanRemote ub; //The Bean
	
	public LoginJB() {
		this.howMuch = ""; // Instantiate the object
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

	final static String htmldelete = "<form name=\"deleteContact\" action=\"index.jsp?deleteContact\" method=\"POST\">" +
			"<input type=\"hidden\" name=\"alias\" value=\"$ALIAS\" />" +
			"<input type=\"hidden\" name=\"contactAddress\" value=\"$ADDRESS\" />" +
			"<input type=\"submit\" value=\"x\" />" +
			"</form>";
	final static String htmlrestorecontact = "<form name=\"restoreContact\" action=\"index.jsp?restoreContact\" method=\"POST\">" +
			"<input type=\"hidden\" name=\"alias\" value=\"$ALIAS\" />" +
			"<input type=\"hidden\" name=\"contactAddress\" value=\"$ADDRESS\" />" +
			"<input type=\"submit\" value=\"restore deleted contact\" />" +
			"</form>"; 
	final static String htmlsendto = "<form name=\"payment\" action=\"index.jsp?contacts&sendTo\" method=\"POST\">" +
			"<input type=\"text\" name=\"howMuch\" value=\"0.00000000\" />" +
			"<input type=\"hidden\" name=\"alias\" value=\"$ALIAS\" />" +
			"<input type=\"hidden\" name=\"contactAddress\" value=\"$ADDRESS\" />" +
			"<input type=\"submit\" value=\"To\" />" +
			"</form>";
	
	public String getContactsTable() {
		List<String> header = new LinkedList<String>();
		header.add("Pay");
		header.add("Alias");
		header.add("Address");
		header.add("Delete");
		
		List<List<String>> cc = contacts.get();
		for(List<String> l : cc) {
			l.add(0, htmlsendto.replace("$ALIAS", l.get(0)).replace("$ADDRESS", l.get(1))); // Old index 1
			l.add(htmldelete.replace("$ALIAS", l.get(1)).replace("$ADDRESS", l.get(2))); // New index 1 (old 0)
		}
		return HTMLUtilities.printTable(cc, header);
	}

	public String getRestoreForm() {
		return htmlrestorecontact.replace("$ALIAS",getAlias()).replace("$ADDRESS", getContactAddress());
	}
	
	public String getContactAddress() {
		return contactAddress;
	}


	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	
	public void addNewContact() {
		try {
			addedContact = ub.newContact(alias, contactAddress);
		} catch (RemoteException e) {
			addedContact = false;
			System.out.println("EJB server problem.");
			e.printStackTrace();
		}
	}
	
	public void removeContact(){
		try {
			setRemovedContact(ub.removeContact(alias));
		} catch (RemoteException e) {
			setRemovedContact(false);
			System.out.println("EJB server problem.");
			e.printStackTrace();
		}
	}
	
	public boolean isContactAddressValid() {
		try {
			return ub.isAddressValid(getContactAddress());
		} catch (RemoteException e) {
			System.out.println("EJB server problem.");
			e.printStackTrace();
		}
		return false;
	}
	
	public void setAddedContact(boolean addedContact) {
		this.addedContact = addedContact;
	}


	public boolean isAddedContact() {
		return addedContact;
	}


	public boolean isRemovedContact() {
		return removedContact;
	}


	public void setRemovedContact(boolean removedContact) {
		this.removedContact = removedContact;
	}

	public void sendTo() {
		System.out.println("LoginJB: "+contactAddress+" "+howMuch);
		BigDecimal hm = null;
		try {
			hm = new BigDecimal(getHowMuch()).setScale(8,BigDecimal.ROUND_HALF_DOWN); // BitCoin format;
			setHowMuch(hm.toPlainString());
		} catch(NumberFormatException e) {
			System.out.println("NumberFormatException: "+getHowMuch());
			setSentTo(false);
			return;
		}
		try {
			Tx tx = ub.sendTo(contactAddress,hm);
			if(tx!=null){
				setSentTo(true);
				BigDecimal f = tx.getFee();
				if(f==null) {
					setFee(new BigDecimal(0));
				}
				else if(f.compareTo(new BigDecimal(0))<=0)
					setFee(f.negate());
				else
					setFee(f);
			} else
				setSentTo(false);
		} catch (RemoteException e) {
			setSentTo(false);
			System.out.println("EJB server problem.");
			e.printStackTrace();
		}
	}
	
	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}


	public boolean isSentTo() {
		return sentTo;
	}

	public void setSentTo(boolean sentTo) {
		this.sentTo = sentTo;
	}


	public String getHowMuch() {
		return howMuch;
	}


	public void setHowMuch(String howMuch) {
		this.howMuch = howMuch;
	}


	public BigDecimal getFee() {
		return fee;
	}


	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
}
