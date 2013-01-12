package org.silix.the9ull.microbit.model;

import javax.persistence.*;

/*
CREATE TABLE `Dict` (
`key` VARCHAR(20) NOT NULL,
`value` VARCHAR(100),
PRIMARY KEY(`key`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
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
		if(key.length()>20)
			this.key = key.substring(0,20);
		else
			this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if(value.length()>100)
			this.value = value.substring(0,100);
		else
			this.value = value;
	}
}
