package com.homedirect.atm.converter;

import com.homedirect.atm.model.Transaction;
import com.homedirect.atm.response.TransactionReponse;

public interface TransactionConverter {

//	Transaction toTransaction();
	
	TransactionReponse toReponse(Transaction transaction);
	
	Transaction toTransaction(int fromAccountId, int toAccountId, double amount, byte type, double fee);
}
