package com.homdirect.application.processor.impl;

import com.homdirect.application.exception.ATMException;
import com.homdirect.application.request.DepositRequest;
import com.homdirect.application.request.TransferRequest;
import com.homdirect.application.request.WithdrawRequest;
import com.homdirect.application.response.TransactionResponse;

public interface TransactionProcessor {

    TransactionResponse deposit(DepositRequest depositRequest) throws ATMException;

    TransactionResponse withdrawal(WithdrawRequest withdrawRequest) throws ATMException;

    TransactionResponse transfer(TransferRequest transferRequest) throws ATMException;

//	Page<TransactionResponse> search(SearchTransactionRequest request) throws ATMException;

//	List<Transaction> findTransactionByAccountId(int accountId) throws ATMException;

//    void exportCsv() throws Exception;
}
