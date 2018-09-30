package com.homedirect.service;

import java.text.ParseException;

import com.homedirect.request.SearchTransactionRequest;
import org.springframework.data.domain.Page;

import com.homedirect.entity.Account;
import com.homedirect.entity.Transaction;
import com.homedirect.exception.ATMException;

public interface TransactionService {

	Transaction deposit(Account account, Double amount) throws ATMException;

	Transaction withdraw(Account account, Double amount) throws ATMException;

	Transaction transfer(Account fromAccount, Account toAccount, Double amount, String content) throws ATMException, ATMException;

	Transaction saveTransaction(String fromAccountNumber, String toAccountNumber, Double transferAmount, String status,
			String content, Byte type);

	Page<Transaction> search(SearchTransactionRequest request)
			throws ATMException;
}
