package com.homedirect.atm.response;

import java.util.Date;

public class TransactionResponse {

	private int fromAccount;
	private int toAccount;
	private byte transactionType;
	private double amount;
	private double fee;
	private Date requestDateTime;
	private byte status;
	
	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public int getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	public byte getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(byte transactionType) {
		this.transactionType = transactionType;
	}

	public Date getRequestDateTime() {
		return requestDateTime;
	}

	public void setRequestDateTime(Date requestDateTime) {
		this.requestDateTime = requestDateTime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
}
