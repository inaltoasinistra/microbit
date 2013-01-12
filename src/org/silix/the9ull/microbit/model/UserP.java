package org.silix.the9ull.microbit.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/*
CREATE TABLE `User` ( 
`id` INT(10) NOT NULL AUTO_INCREMENT,
`deposit_address` VARCHAR(34),
`password` VARCHAR(40) NOT NULL, 
`fund` DECIMAL(18,8) UNSIGNED,
`email` VARCHAR(50),  
PRIMARY KEY (`id`),
KEY (`deposit_address`),
UNIQUE KEY (`deposit_address`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1; 
 */

@Entity
@Table (name="User")
public class UserP {

	@Id
	private int id;
	private String deposit_address;
	private String password;
	private String email;
	private BigDecimal fund;
	private Set<ContactP> contacts = new HashSet<ContactP>(0);
	private Set<HistoryP> history_from = new HashSet<HistoryP>(0);
	private Set<HistoryP> history_to = new HashSet<HistoryP>(0);

	
	public UserP(){
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserP other = (UserP) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public String getDeposit_address() {
		return deposit_address;
	}

	public void setDeposit_address(String deposit_address) {
		if(deposit_address.length()>34)
			this.deposit_address = deposit_address.substring(0,34);
		else
			this.deposit_address = deposit_address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password.length()>40)
			this.password = password.substring(0,40);
		else
			this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email.length()>50)
			this.email = email.substring(0,50);
		else
			this.email = email;
	}

	public BigDecimal getFund() {
		return fund;
	}

	public void setFund(BigDecimal fund) {
		this.fund = fund;
	}

	public Set<HistoryP> getHistory_from() {
		return history_from;
	}

	public void setHistory_from(Set<HistoryP> history_from) {
		this.history_from = history_from;
	}

	public Set<HistoryP> getHistory_to() {
		return history_to;
	}

	public void setHistory_to(Set<HistoryP> history_to) {
		this.history_to = history_to;
	}

	public Set<ContactP> getContacts() {
		return contacts;
	}

	public void setContacts(Set<ContactP> contacts) {
		this.contacts = contacts;
	}

}
