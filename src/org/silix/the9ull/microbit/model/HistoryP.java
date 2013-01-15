package org.silix.the9ull.microbit.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
CREATE TABLE `History` ( 
`id` INT(10) NOT NULL AUTO_INCREMENT,
`from` INT(10) NOT NULL,
`to` INT(10) NOT NULL,
`when` DATETIME,
`howmuch` DECIMAL(18,8),
`fee` DECIMAL(18,8),
`txidcrc` BIGINT,
PRIMARY KEY (`id`),
KEY (`txidcrc`),
KEY `FK_fromto` (`from`), 
CONSTRAINT `FK_from` FOREIGN KEY (`from`) REFERENCES `User` (`id`),
CONSTRAINT `FK_to` FOREIGN KEY (`to`) REFERENCES `User` (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
 */

@Entity
@Table (name="History")
public class HistoryP implements Comparable, Serializable {

	@Id
	private int id;
	private UserP from;
	private UserP to;
	private Date when;
	private BigDecimal howmuch;
	private BigDecimal fee;
	private long txidcrc;
	
	public HistoryP() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserP getFrom() {
		return from;
	}

	public void setFrom(UserP from) {
		this.from = from;
	}

	public UserP getTo() {
		return to;
	}

	public void setTo(UserP to) {
		this.to = to;
	}

	public Date getWhen() {
		return when;
	}

	public void setWhen(Date when) {
		this.when = when;
	}

	public BigDecimal getHowmuch() {
		return howmuch;
	}

	public void setHowmuch(BigDecimal howmuch) {
		this.howmuch = howmuch!=null ? howmuch.setScale(8,BigDecimal.ROUND_HALF_DOWN) : null;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee!=null ? fee.setScale(8,BigDecimal.ROUND_HALF_DOWN) : null;
	}

	public long getTxidcrc() {
		return txidcrc;
	}

	public void setTxidcrc(long txidcrc) {
		this.txidcrc = txidcrc;
	}

	@Override
	public String toString() {
		return "HistoryP [id=" + id + ", from=" + from + ", to=" + to
				+ ", when=" + when + ", howmuch=" + howmuch + ", txidcrc="
				+ txidcrc + "]";
	}

	@Override
	public int compareTo(Object arg0) {
		HistoryP o = (HistoryP) arg0;
		return this.getWhen().compareTo(o.getWhen());
	}


}
