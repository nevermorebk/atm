package com.homedirect.atm.services.impl;

import com.homedirect.atm.converter.TransactionConverter;
import com.homedirect.atm.model.Transaction;
import com.homedirect.atm.repository.TransactionRepository;
import com.homedirect.atm.response.TransactionReponse;
import com.homedirect.atm.services.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	private TransactionRepository transactionRepository;

//	@Autowired
//	private TransactionConverter transactionConverter;

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
	public List<Transaction> transactionType(Integer id, Byte type) {
		if (id == null && type == null) {
			return transactionRepository.findAll();
		}

		if (type == null) {
			return transactionRepository.getByFromAccount(id);
		}

		if (id == null) {
			return transactionRepository.getByTransactionType(type);
		}

		return transactionRepository.getByFromAccountAndTransactionType(id, type);
	}
}
