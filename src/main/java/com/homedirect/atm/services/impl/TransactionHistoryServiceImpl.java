package com.homedirect.atm.services.impl;

import com.homedirect.atm.model.Account;
import com.homedirect.atm.model.Transaction;
import com.homedirect.atm.model.Transaction.TransactionType;
import com.homedirect.atm.repository.AccountRepository;
import com.homedirect.atm.repository.TransactionRepository;
import com.homedirect.atm.services.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.homedirect.atm.model.Transaction.TransactionType.WITHDRAW;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	private @Autowired TransactionRepository transactionRepository;

	private @Autowired AccountRepository accountRepository;

	@Override
	public void saveTransaction(byte transactionType, int fromAccountId, int toAccountId, double amount, double fee,
			byte type, byte status) {
		Transaction transaction = new Transaction();

		transaction.setTransactionType(transactionType);
		transaction.setFromAccount(fromAccountId);
		transaction.setToAccount(toAccountId);
		transaction.setAmount(amount);
		transaction.setTransactionType(type);
		transaction.setFee(fee);
		transaction.setRequestDatetime(new Date());
		transaction.setStatus(status);
		transactionRepository.save(transaction);
	}

	@Override
	public String depositHistory(int id) {
		Transaction transaction = null;
		Optional<Account> account = accountRepository.findById(id);
		for (int i = 0; i < transactionRepository.findAll().size(); i++) {
			 transaction = transactionRepository.findAll().get(i);
			if (conditionPrintHistory(transaction, TransactionType.DEPOSIT, account.get())) {
				System.out.println("Not found");
			}
		}

		return transaction.printDeposit();
	}

	@Override
	public List<Transaction> withdrawalHistory(int id) {
		return transactionRepository.getByFromAccountAndTransactionType(id, WITHDRAW);
	}

	@Override
	public String transferHistory(int id) {
		Transaction transaction = null;
		Optional<Account> account = accountRepository.findById(id);
		for (int i = 0; i < transactionRepository.findAll().size(); i++) {
			transaction = transactionRepository.findAll().get(i);
			if (!conditionPrintHistory(transaction, TransactionType.TRANSFER, account.get())) {
				System.out.println("Not found");
			}
		}

		return transaction.printTransfer();
	}

	@Override
	public String receiveHistory(int id) {
		Transaction transaction = null;
		Optional<Account> account = accountRepository.findById(id);
		for (int i = 0; i < transactionRepository.findAll().size(); i++) {
			transaction = transactionRepository.findAll().get(i);
			if (conditionPrintHistoryReceive(transaction, TransactionType.TRANSFER, account.get())) {
				System.out.println("Not found");
			}
		}

		return transaction.printReceive();
	}

	public boolean conditionPrintHistory(Transaction transaction, int type, Account account) {
		return (transaction.getTransactionType() == type && account.getId() == (transaction.getFromAccount()));
	}

	private boolean conditionPrintHistoryReceive(Transaction transaction, byte type, Account account) {
		return (transaction.getTransactionType() == type && transaction.getFromAccount() != account.getId()
				&& transaction.getToAccount() == (account.getId()));
	}
}
