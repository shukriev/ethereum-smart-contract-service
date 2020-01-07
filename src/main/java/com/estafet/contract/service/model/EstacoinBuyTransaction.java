package com.estafet.contract.service.model;

import java.math.BigInteger;

public class EstacoinBuyTransaction {
	private BigInteger amount;
	
	private String address;
	
	public BigInteger getAmount() {
		return amount;
	}
	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
