package org.silix.the9ull.microbit.model;

/*
CREATE TABLE `Contact` ( 
`id_user` INT(10) NOT NULL,
`alias` VARCHAR(50) NOT NULL,
`address` VARCHAR(34), 
PRIMARY KEY (`id_user`,`alias`), 
KEY `FK_id` (`id_user`), 
CONSTRAINT `FK_id` FOREIGN KEY (`id_user`) REFERENCES `User` (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
*/

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name="Contact")
public class ContactP implements Serializable, Comparable<ContactP> {

	private static final long serialVersionUID = -6578476539882495610L;
	
	@Id
	private UserP user;
	@Id
	private String alias;
	private String address;
	
	
	public ContactP() {
		
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		if(alias.length()>50)
			this.alias = alias.substring(0, 50);
		else
			this.alias = alias;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if(address.length()>34)
			this.address = address.substring(0,34);
		else
			this.address = address;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UserP getUser() {
		return user;
	}

	public void setUser(UserP user) {
		this.user = user;
	}
	
	@Override
	public boolean equals(Object o){
		ContactP c = (ContactP)o;
		return this.getUser().getId() == c.getUser().getId() && this.getAlias() == c.getAlias();
	}
	@Override
	public int hashCode() {
		return this.getUser().hashCode() ^ this.getAlias().hashCode();
	}

	@Override
	public int compareTo(ContactP o) {
		return this.getAlias().compareTo(o.getAlias());
	}
	
	
}
