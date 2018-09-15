package com.homedirect.atm.request;

import javax.validation.constraints.NotBlank;

public class TransferRequest {

	private int toId;
	private int fromId;
	private double amount;

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
