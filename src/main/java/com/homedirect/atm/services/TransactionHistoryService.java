package com.homedirect.atm.services;

import com.homedirect.atm.model.Transaction;
import java.util.List;

public interface TransactionHistoryService {

	Transaction saveTransaction(int fromAccountId, int toAccountId, double amount, double fee, byte type, byte status);
	
	List<Transaction> transactionType(Integer id, Byte type);
}
