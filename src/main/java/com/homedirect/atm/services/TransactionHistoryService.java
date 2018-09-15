package com.homedirect.atm.services;

import com.homedirect.atm.model.Transaction;

import java.util.List;

public interface TransactionHistoryService {

	void saveTransaction(byte typeTransaction, int fromAccountId, int toAccountId, double amount, double fee, byte type, byte status);
	
	String depositHistory(int id);

	List<Transaction> withdrawalHistory(int id);
	
	String transferHistory(int id);
	
	String receiveHistory(int id);
}
