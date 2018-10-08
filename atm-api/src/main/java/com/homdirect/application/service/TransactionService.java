package com.homdirect.application.service;

import com.homdirect.application.entity.Account;
import com.homdirect.application.entity.Transaction;
import com.homdirect.application.exception.ATMException;

import java.util.List;

public interface TransactionService {

    Transaction deposit(Account account, Double amount) throws ATMException;

    Transaction withdraw(Account account, Double amount) throws ATMException;

    Transaction transfer(Account fromAccount, Account toAccount, Double amount, String content) throws ATMException, ATMException;

    Transaction saveTransaction(String fromAccountNumber, String toAccountNumber, Double transferAmount, String status,
                                String content, Byte type);

//	Page<Transaction> search(int accountId, String fromDate, String toDate, Byte type, int pageNo, int pageSize)
//			throws ATMException;

    List<Transaction> findTransactionByAccountNumber(String accountNumber);

    List<Transaction> findAll();
}
