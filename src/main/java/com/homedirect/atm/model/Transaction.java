package com.homedirect.atm.model;

import com.homedirect.atm.util.NumberUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int fromAccount;
	private int toAccount;
	private byte transactionType;
	private double amount;
	private double fee;
	private byte status;
	private Date requestDatetime;

	public class StatusType {
		public static final byte SUCCESS = 1;
		public static final byte FAILURE = -1;
	}

	public class TransactionType {
		public static final byte DEPOSIT = 1;
		public static final byte WITHDRAW = 2;
		public static final byte TRANSFER = 3;
		public static final byte RECEIVE = 4;
	}

	public Transaction() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getRequestDatetime() {
		return requestDatetime;
	}

	public void setRequestDatetime(Date requestDatetime) {
		this.requestDatetime = requestDatetime;
	}

	public String printDeposit() {
		return "Transaction type:  Deposit " + "   Date: " + requestDatetime + "    Amount: +"
				+ NumberUtils.formatAmount(amount) + " VND " + "   Status: " + status + "\n";
	}

	public String printWithdraw() {
		return "Transaction Type:  Withdraw " + "  Date: " + requestDatetime + "    Amount: -"
				+ NumberUtils.formatAmount(amount) + " VND " + "    Fee: " + NumberUtils.formatAmount(fee) + " VND "
				+ "   Status: " + status + " \n";
	}

	public String printTransfer() {
		return "Transaction Type:  Transaction " + "  Date: " + requestDatetime + "    Amount: -"
				+ NumberUtils.formatAmount(amount) + " VND " + "    Fee: " + NumberUtils.formatAmount(fee) + " VND "
				+ "   Status: " + status + "\n";
	}

	public String printReceive() {
		return "Transaction Type:  Receive " + "	from Account ID: " + fromAccount + "   Date: " + requestDatetime
				+ "   Amount: +" + NumberUtils.formatAmount(amount) + " VND " + "   Status: " + status + "\n";
	}
}
