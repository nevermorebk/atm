package com.homedirect.atm.services.impl;

import com.homedirect.atm.model.Transaction;
import com.homedirect.atm.repository.TransactionRepository;
import com.homedirect.atm.response.TransactionResponse;
import com.homedirect.atm.services.TransactionHistoryService;
import com.homedirect.atm.transformer.TransactionTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionTransformer transactionTransformer;

	@Override
	public Transaction saveTransaction(int fromAccountId, int toAccountId, double amount, double fee, byte type, byte status) {
		Transaction transaction = new Transaction();

		transaction.setFromAccount(fromAccountId);
		transaction.setToAccount(toAccountId);
		transaction.setAmount(amount);
		transaction.setTransactionType(type);
		transaction.setFee(fee);
		transaction.setRequestDatetime(new Date());
		transaction.setStatus(status);
		return transactionRepository.save(transaction);
	}
	
	@Override
	public List<TransactionResponse> transactionType(Integer id, Byte type) {
		if (id == null && type == null) {
			List<Transaction> transactions = transactionRepository.findAll();
			return transactionTransformer.toResponses(transactions);
		}

		if (type == null) {
			List<Transaction> transactions = transactionRepository.getByFromAccount(id);
			return transactionTransformer.toResponses(transactions);
		}

		if (id == null) {
			List<Transaction> transactions = transactionRepository.getByTransactionType(type);
			return transactionTransformer.toResponses(transactions);
		}

		List<Transaction> transactions = transactionRepository.getByFromAccountAndTransactionType(id, type);
		return transactionTransformer.toResponses(transactions);
	}
}
