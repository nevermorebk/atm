package com.homedirect.atm.transformer;

import java.util.List;

import com.homedirect.atm.model.Transaction;
import com.homedirect.atm.response.TransactionResponse;

public interface TransactionTransformer {

	TransactionResponse toResponse(Transaction transaction);
	
	List<TransactionResponse> toResponses(List<Transaction> transactions);
	
	Transaction toTransaction(int fromAccountId, int toAccountId, double amount, double fee, byte type);
}
