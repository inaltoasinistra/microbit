package org.silix.the9ull.microbit.model;

import java.math.BigDecimal;
import java.util.Date;

public class Tx {

	public Tx() {
		
	}
	private String txid;
	private BigDecimal fee;
	private BigDecimal amount;
	private String address;
	private String category;
	private Date when;
	
	private String strError;

	@Override
	public String toString() {
		return "Tx [txid=" + txid + ", fee=" + fee + ", amount=" + amount
				+ ", address=" + address + ", category=" + category + ", when="
				+ when + ", strError=" + strError + "]";
	}

	public String getTxid() {
		return txid;
	}
	
	public void setTxid(String txid) {
		this.txid = txid;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public Date getWhen() {
		return when;
	}

	public void setWhen(Date when) {
		this.when = when;
	}

	public String getStrError() {
		return strError;
	}

	public void setStrError(String strError) {
		this.strError = strError;
	}
	
}

