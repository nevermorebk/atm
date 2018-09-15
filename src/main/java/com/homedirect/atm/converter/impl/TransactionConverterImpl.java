package com.homedirect.atm.converter.impl;

import com.homedirect.atm.converter.TransactionConverterNew;
import com.homedirect.atm.model.Transaction;
import com.homedirect.atm.response.TransactionReponse;

public class TransactionConverterImpl implements TransactionConverterNew {

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
