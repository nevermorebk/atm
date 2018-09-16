package com.homedirect.atm.converter.impl;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.homedirect.atm.converter.TransactionConverter;
import com.homedirect.atm.model.Transaction;
import com.homedirect.atm.response.TransactionReponse;

@Component
public class TransactionConverterImpl implements TransactionConverter {
	
	public Transaction toTransaction(int fromAccountId, int toAccountId, double amount, byte type, double fee) {
		Transaction transaction = new Transaction();
		transaction.setFromAccount(fromAccountId);
		transaction.setToAccount(toAccountId);
		transaction.setAmount(amount);
		transaction.setStatus(Transaction.Status.SUCCESS);
		transaction.setTransactionType(type);
		transaction.setRequestDatetime(new Date());
		transaction.setFee(fee);
		return transaction;
	}

	@Override
	public TransactionReponse toReponse(Transaction transaction) {
		TransactionReponse reponse = new TransactionReponse();
		reponse.setId(transaction.getId());
		reponse.setFromAccount(transaction.getFromAccount());
		reponse.setToAccount(transaction.getToAccount());
		reponse.setAmount(transaction.getAmount());
		reponse.setStatus(transaction.getStatus());
		reponse.setRequestDateTime(transaction.getRequestDatetime());
		return reponse;
	}
}
