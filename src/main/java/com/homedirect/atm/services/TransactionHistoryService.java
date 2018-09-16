package com.homedirect.atm.services;

import com.homedirect.atm.model.Transaction;
import com.homedirect.atm.response.TransactionResponse;

import java.util.List;

public interface TransactionHistoryService {

	Transaction saveTransaction(int fromAccountId, int toAccountId, double amount, double fee, byte type, byte status);
	
	List<TransactionResponse> transactionType(Integer id, Byte type);
}
