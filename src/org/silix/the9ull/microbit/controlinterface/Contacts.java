package org.silix.the9ull.microbit.controlinterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;

import org.silix.the9ull.microbit.control.UserBeanRemote;
import org.silix.the9ull.microbit.model.ContactP;

public class Contacts {

	private UserBeanRemote ub;
	
	public Contacts(UserBeanRemote ub) {
		this.ub = ub;
	}
	
	public List<List<String>> get() {
		List< List<String> > contacts = new LinkedList<List<String>>();
		try {
			//Sorting Contacts
			List<ContactP> contactsList = new ArrayList<ContactP>(ub.getContacts());
			Collections.sort(contactsList);
			for(ContactP c : contactsList) {
				List<String> l = new LinkedList<String>();
				l.add(c.getAlias());
				l.add(c.getAddress());
				contacts.add(l);
			}
		} catch (RemoteException e) {
			System.out.println("Contacts: EJB server problem");
			e.printStackTrace();
		}
		//Sort!
		return contacts;
	}
	
	public void logout() {
		// dereference contacts instance
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
