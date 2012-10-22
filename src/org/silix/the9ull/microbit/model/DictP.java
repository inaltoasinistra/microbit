package org.silix.the9ull.microbit.model;

import javax.persistence.*;

/*
CREATE TABLE `Dict` (
`key` VARCHAR(20) NOT NULL,
`value` VARCHAR(100),
PRIMARY KEY(`key`) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1; 
 */

@Entity
@Table (name="Dict")
public class DictP {

	@Id
	private String key;
	private String value;
	
	public DictP() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
