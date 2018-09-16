package com.homedirect.atm.transformer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homedirect.atm.model.Transaction;
import com.homedirect.atm.repository.TransactionRepository;
import com.homedirect.atm.response.TransactionResponse;
import com.homedirect.atm.transformer.TransactionTransformer;

@Component
public class TransactionTransformerImpl implements TransactionTransformer {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Transaction toTransaction(int fromAccountId, int toAccountId, double amount, double fee, byte type) {
		Transaction transaction = new Transaction();
		transaction.setFromAccount(fromAccountId);
		transaction.setToAccount(toAccountId);
		transaction.setAmount(amount);
		transaction.setTransactionType(type);
		transaction.setFee(fee);
		transaction.setRequestDatetime(new Date());
		transaction.setStatus(Transaction.Status.SUCCESS);
		return transactionRepository.save(transaction);
	}

	@Override
	public TransactionResponse toResponse(Transaction transaction) {
		TransactionResponse reponse = new TransactionResponse();
		reponse.setFromAccount(transaction.getFromAccount());
		reponse.setToAccount(transaction.getToAccount());
		reponse.setAmount(transaction.getAmount());
		reponse.setStatus(transaction.getStatus());
		reponse.setRequestDateTime(transaction.getRequestDatetime());
		return reponse;
	}
	
	public List<TransactionResponse> toResponses(List<Transaction> transactions) {
		if (transactions == null) return new ArrayList<TransactionResponse>();
		List<TransactionResponse> responses = new ArrayList<>();
		transactions.forEach(transaction-> {
			TransactionResponse reponse = new TransactionResponse();
			reponse.setFromAccount(transaction.getFromAccount());
			reponse.setToAccount(transaction.getToAccount());
			reponse.setAmount(transaction.getAmount());
			reponse.setTransactionType(transaction.getTransactionType());
			reponse.setFee(transaction.getFee());
			reponse.setStatus(transaction.getStatus());
			reponse.setRequestDateTime(transaction.getRequestDatetime());
			responses.add(reponse);
		});
		
		return responses;
	}
}
