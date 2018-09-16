package com.homedirect.atm.services.impl;

import com.homedirect.atm.model.Account;
import com.homedirect.atm.model.Transaction.Status;
import com.homedirect.atm.model.Transaction.TransactionType;
import com.homedirect.atm.repository.AccountRepository;
import com.homedirect.atm.request.DepositRequest;
import com.homedirect.atm.request.TransferRequest;
import com.homedirect.atm.request.WithdrawalRequest;
import com.homedirect.atm.response.AccountResponse;
import com.homedirect.atm.services.TransactionHistoryService;
import com.homedirect.atm.transformer.AccountTransformer;
import com.homedirect.atm.services.PaymentService;
import com.homedirect.atm.validator.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.homedirect.atm.commons.ConfigConstants.*;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private TransactionHistoryService transactionHistory;

	@Autowired
	private AccountValidator accountValidator;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountTransformer accountTransformer;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public AccountResponse deposit(DepositRequest request) {
		if (accountValidator.inValidAmount(request.getAmount())) {
			return null;
		}
		Account account = accountRepository.findById(request.getId());
		account.setAmount(account.getAmount() + request.getAmount());
		System.out.println(" \n Deposit successfully! \n");

		transactionHistory.saveTransaction(request.getId(), NA, request.getAmount(),
				NO_FEE, TransactionType.DEPOSIT, Status.SUCCESS);
		return accountTransformer.toResponse(account);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public AccountResponse withdrawal(WithdrawalRequest request) {
		Account account = accountRepository.findById(request.getId());
		if (accountValidator.isValidPaymentAmount(account, request.getAmount())) {
			System.out.println(" \n Not enough balance! \n");
			return null;
		}

		account.setAmount(account.getAmount() - (request.getAmount() + FEE));
		System.out.println(" \n Withdrawal successfully! \n");

		transactionHistory.saveTransaction(request.getId(), NA, request.getAmount(),
				FEE, TransactionType.WITHDRAW,Status.SUCCESS);
		return accountTransformer.toResponse(account);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public AccountResponse transfer(TransferRequest request) {
		Account toAccount = accountRepository.findById(request.getToId());
		Account fromAccount = accountRepository.findById(request.getFromId());
		if (accountValidator.isValidPaymentAmount(fromAccount, request.getAmount())) {
			System.out.println(" \n Not enough balance! \n");
			return null;
		}

		toAccount.setAmount(toAccount.getAmount() + request.getAmount());
		fromAccount.setAmount(fromAccount.getAmount() - (request.getAmount() + FEE));
		System.out.println(" \n Transfer successfully! \n");

		transactionHistory.saveTransaction(request.getFromId(), request.getToId(), request.getAmount(),
				FEE,TransactionType.TRANSFER, Status.SUCCESS);
		return accountTransformer.toResponse(fromAccount);
	}
}
