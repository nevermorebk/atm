package com.homdirect.application.processor.impl.impl;

import com.homdirect.application.entity.Account;
import com.homdirect.application.entity.Transaction;
import com.homdirect.application.exception.ATMException;
import com.homdirect.application.processor.impl.TransactionProcessor;
import com.homdirect.application.request.DepositRequest;
import com.homdirect.application.request.TransferRequest;
import com.homdirect.application.request.WithdrawRequest;
import com.homdirect.application.response.TransactionResponse;
import com.homdirect.application.service.AccountService;
import com.homdirect.application.service.TransactionService;
import com.homdirect.application.transformer.TransactionTransformer;
import com.homdirect.application.validator.InputValidator;
import com.homdirect.application.validator.impl.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionProcessorImpl implements TransactionProcessor {

    private final TransactionService service;
    private final AccountService accountService;
    private final TransactionTransformer transformer;
    private final InputValidator validatorInputATM;

    @Autowired
    public TransactionProcessorImpl(TransactionService service, AccountService accountService, TransactionTransformer transformer, Input validatorInputATM) {
        this.service = service;
        this.accountService = accountService;
        this.transformer = transformer;
        this.validatorInputATM = validatorInputATM;
    }

    @Override
    public TransactionResponse deposit(DepositRequest depositRequest) throws ATMException {
        Account account = accountService.findById(depositRequest.getId());
        Double amount = depositRequest.getAmount();
        validatorInputATM.validatorDeposit(amount);
        Transaction transaction = service.deposit(account, amount);
        return transformer.toResponse(transaction);
    }

    @Override
    public TransactionResponse withdrawal(WithdrawRequest withdrawRequest) throws ATMException {
        Double amount = withdrawRequest.getAmount();
        Account account = accountService.findById(withdrawRequest.getId());
        validatorInputATM.validatorWithdraw(amount, account.getAmount());

        Input.checkPasswordByAccount(withdrawRequest.getPassword(), account);

        Transaction transaction = service.withdraw(account, amount);
        return transformer.toResponse(transaction);
    }

    @Override
    public TransactionResponse transfer(TransferRequest request) throws ATMException {
        Account fromAccount = accountService.findById(request.getFromId());
        Account toAccount = validatorInputATM.isValidateInputTransfer(request.getToAccountNumber());
        validatorInputATM.checkTransfer(toAccount.getId(), request.getFromId());
        Input.checkPasswordByAccount(request.getPassword(), fromAccount);
//
//		accountService.save(fromAccount);
//		accountService.save(toAccount);
        Transaction transaction = service.transfer(fromAccount, toAccount, request.getAmount(), request.getContent());
        return transformer.toResponse(transaction);
    }

//	@Override
//	public Page<TransactionResponse> search(SearchTransactionRequest request) throws ATMException {
//		return transformer.toResponse(service.search(request.getAccountId(), request.getFromDate(), request.getToDate(),
//				request.getType(), request.getPageNo(), request.getPageSize()));
//
//	}

//	public List<Transaction> findTransactionByAccountId(int accountId) throws ATMException {
//		Account account = accountService.findById(accountId);
//		String accountNumber = account.getAccountNumber();
//		QTransaction transaction = QTransaction.transaction;
//		BooleanBuilder where = new BooleanBuilder();
//
//		if (accountNumber != null) {
//			where.and(transaction.fromAccount.eq(accountNumber));
//		}
//		return service.findTransactionByAccountNumber(accountNumber);
//	}

    public List<Transaction> findAll() {
        return service.findAll();
    }

//    @Override
//    public void exportCsv() throws Exception {
//        service.exportCsv();
//    }
}
